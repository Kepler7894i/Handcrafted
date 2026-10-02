import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Adds the Handcrafted artwork (textures, models, sounds, language files, icon) to a code-only Handcrafted jar.
 *
 * The artwork is All Rights Reserved by its original authors, so it is not part of this repository or its
 * releases. This tool takes it from the original Handcrafted 1.21.1 jar, which it downloads from Modrinth onto
 * your own machine, and converts it to the Minecraft 26.x format on the fly.
 *
 * Usage:
 *   java tools/AssetInstaller.java &lt;handcrafted jar | folder containing one or more&gt; [--original original.jar] [--mc-jar 26.2.jar]
 *
 * The jar(s) are modified in place.
 */
public class AssetInstaller {

    private static final String PROJECT = "handcrafted";
    private static final String ORIGINAL_VERSION = "4.0.3";
    private static final String ORIGINAL_GAME_VERSION = "1.21.1";
    private static final String MC_VERSION = "26.2";
    private static final String BOW_TARGET = "assets/handcrafted/textures/block/trophy/vanilla/bow.png";

    public static void main(String[] args) throws Exception {
        Path target = null;
        Path original = null;
        Path mcJar = null;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--original" -> original = Path.of(args[++i]);
                case "--mc-jar" -> mcJar = Path.of(args[++i]);
                default -> target = Path.of(args[i]);
            }
        }
        if (target == null) {
            System.err.println("Usage: java tools/AssetInstaller.java <handcrafted jar or mods folder> [--original original.jar] [--mc-jar " + MC_VERSION + ".jar]");
            System.exit(2);
        }

        List<Path> jars = new ArrayList<>();
        if (Files.isDirectory(target)) {
            try (Stream<Path> s = Files.list(target)) {
                s.filter(p -> p.getFileName().toString().matches("handcrafted-(fabric|neoforge)-.*\\.jar")
                        && !p.getFileName().toString().endsWith("-sources.jar"))
                    .forEach(jars::add);
            }
        } else {
            jars.add(target);
        }
        if (jars.isEmpty()) {
            System.err.println("No handcrafted-fabric-*/handcrafted-neoforge-* jar found in " + target);
            System.exit(1);
        }

        if (original == null) original = downloadOriginal();
        if (mcJar == null) mcJar = findMinecraftJar();

        Map<String, byte[]> assets = convert(original, mcJar);
        for (Path jar : jars) {
            inject(jar, assets);
            System.out.println("Installed " + assets.size() + " asset files into " + jar);
        }
    }

    // ---------------------------------------------------------------- download

    private static Path downloadOriginal() throws Exception {
        Path cache = Path.of(System.getProperty("user.home"), ".cache", "handcrafted-assets");
        Files.createDirectories(cache);
        HttpClient http = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();

        String api = "https://api.modrinth.com/v2/project/" + PROJECT + "/version?loaders=%5B%22fabric%22%5D&game_versions=%5B%22"
            + ORIGINAL_GAME_VERSION + "%22%5D";
        String json = http.send(HttpRequest.newBuilder(URI.create(api)).header("User-Agent", "handcrafted-asset-installer").build(),
            HttpResponse.BodyHandlers.ofString()).body();

        // Find the entry for ORIGINAL_VERSION and read its file url + sha512 without a JSON library.
        int at = json.indexOf("\"version_number\":\"" + ORIGINAL_VERSION + "\"");
        if (at < 0) throw new IOException("Handcrafted " + ORIGINAL_VERSION + " for " + ORIGINAL_GAME_VERSION + " not found on Modrinth");
        String section = json.substring(at);
        String url = group(section, "\"url\":\"([^\"]+\\.jar)\"");
        String sha512 = group(section, "\"sha512\":\"([0-9a-f]+)\"");
        String name = url.substring(url.lastIndexOf('/') + 1);

        Path file = cache.resolve(name);
        if (!Files.exists(file) || !sha512(file).equals(sha512)) {
            System.out.println("Downloading original Handcrafted " + ORIGINAL_VERSION + " (" + name + ") from Modrinth...");
            Path tmp = cache.resolve(name + ".part");
            http.send(HttpRequest.newBuilder(URI.create(url)).header("User-Agent", "handcrafted-asset-installer").build(),
                HttpResponse.BodyHandlers.ofFile(tmp));
            if (!sha512(tmp).equals(sha512)) {
                Files.deleteIfExists(tmp);
                throw new IOException("Checksum mismatch for downloaded " + name);
            }
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
        } else {
            System.out.println("Using cached " + file);
        }
        return file;
    }

    private static String group(String text, String regex) throws IOException {
        Matcher m = Pattern.compile(regex).matcher(text);
        if (!m.find()) throw new IOException("Unexpected Modrinth response (" + regex + ")");
        return m.group(1);
    }

    private static String sha512(Path file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        try (InputStream in = Files.newInputStream(file)) {
            byte[] buf = new byte[1 << 16];
            for (int n; (n = in.read(buf)) > 0; ) md.update(buf, 0, n);
        }
        return HexFormat.of().formatHex(md.digest());
    }

    /** Looks for a launcher version folder for this Minecraft version whose jar contains the vanilla assets. */
    private static Path findMinecraftJar() {
        List<Path> roots = new ArrayList<>();
        String appData = System.getenv("APPDATA");
        if (appData != null) roots.add(Path.of(appData, ".minecraft"));
        roots.add(Path.of(System.getProperty("user.home"), ".minecraft"));
        roots.add(Path.of(System.getProperty("user.home"), "Library", "Application Support", "minecraft"));
        for (Path root : roots) {
            Path versions = root.resolve("versions");
            if (!Files.isDirectory(versions)) continue;
            // Exact vanilla folder first, then modded profiles for this version (e.g. fabric-loader-x-26.2).
            List<Path> jars = new ArrayList<>();
            jars.add(versions.resolve(MC_VERSION).resolve(MC_VERSION + ".jar"));
            try (Stream<Path> dirs = Files.list(versions)) {
                dirs.filter(d -> d.getFileName().toString().endsWith("-" + MC_VERSION))
                    .forEach(d -> jars.add(d.resolve(d.getFileName() + ".jar")));
            } catch (IOException ignored) {
            }
            for (Path jar : jars) {
                if (!Files.exists(jar)) continue;
                try (ZipFile zip = new ZipFile(jar.toFile())) {
                    if (zip.getEntry("assets/minecraft/textures/item/bow.png") != null) return jar;
                } catch (IOException ignored) {
                }
            }
        }
        return null;
    }

    // ---------------------------------------------------------------- conversion (1.21.1 assets -> 26.x assets)

    private static Map<String, byte[]> convert(Path original, Path mcJar) throws IOException {
        Map<String, byte[]> out = new java.util.TreeMap<>();
        List<String> itemModels = new ArrayList<>();

        try (ZipFile zip = new ZipFile(original.toFile())) {
            for (var entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry e = entries.nextElement();
                String name = e.getName();
                if (e.isDirectory()) continue;
                if (!name.startsWith("assets/") && !name.equals("icon.png")) continue;

                byte[] data = zip.getInputStream(e).readAllBytes();
                if (name.startsWith("assets/handcrafted/models/") && name.endsWith(".json")) {
                    data = fixModel(name, new String(data, StandardCharsets.UTF_8)).getBytes(StandardCharsets.UTF_8);
                }
                if (name.startsWith("assets/handcrafted/models/item/") && name.endsWith(".json")) {
                    itemModels.add(name.substring("assets/handcrafted/models/item/".length(), name.length() - ".json".length()));
                }
                out.put(name, data);
            }
        }

        // 1.21.4+ resolves items through assets/<ns>/items/<id>.json instead of models/item/<id>.json.
        for (String id : itemModels) {
            String def = "{\n  \"model\": {\n    \"type\": \"minecraft:model\",\n    \"model\": \"handcrafted:item/" + id + "\"\n  }\n}\n";
            out.put("assets/handcrafted/items/" + id + ".json", def.getBytes(StandardCharsets.UTF_8));
        }

        // The skeleton trophy used the vanilla item atlas, which block models can no longer reference,
        // so the bow texture is copied from the player's own Minecraft install.
        if (mcJar != null) {
            try (ZipFile zip = new ZipFile(mcJar.toFile())) {
                ZipEntry bow = zip.getEntry("assets/minecraft/textures/item/bow.png");
                if (bow != null) out.put(BOW_TARGET, zip.getInputStream(bow).readAllBytes());
            }
        }
        if (!out.containsKey(BOW_TARGET)) {
            System.err.println("WARNING: Minecraft " + MC_VERSION + " jar not found (use --mc-jar); the skeleton trophy will be missing its bow texture.");
        }
        return out;
    }

    private static String fixModel(String name, String json) {
        // Out-of-range UVs are now rejected.
        json = json.replace("\"uv\": [-5, 1, 1, 1.5]", "\"uv\": [0, 1, 6, 1.5]");
        // Renamed in Minecraft 1.21.9.
        json = json.replace("\"minecraft:block/chain\"", "\"minecraft:block/iron_chain\"");
        // Models without a particle texture now log warnings.
        if (name.startsWith("assets/handcrafted/models/block/fancy_bed/")) {
            json = json.replace("\"0\": \"#frame\",", "\"particle\": \"#frame\",\n\t\t\"0\": \"#frame\",");
        }
        // Block models can no longer use item-atlas textures.
        json = json.replace("\"minecraft:item/bow\"", "\"handcrafted:block/trophy/vanilla/bow\"");
        return json;
    }

    // ---------------------------------------------------------------- jar injection

    private static void inject(Path jar, Map<String, byte[]> assets) throws IOException {
        try (FileSystem fs = FileSystems.newFileSystem(jar)) {
            Path assetsDir = fs.getPath("assets");
            if (Files.exists(assetsDir)) {
                // Replace any previously injected assets (but not anything a code-only jar might ship).
                try (Stream<Path> s = Files.walk(fs.getPath("assets", "handcrafted")).sorted(java.util.Comparator.reverseOrder())) {
                    for (Path p : (Iterable<Path>) s::iterator) Files.deleteIfExists(p);
                } catch (java.nio.file.NoSuchFileException ignored) {
                }
            }
            for (Map.Entry<String, byte[]> e : assets.entrySet()) {
                Path p = fs.getPath(e.getKey());
                if (p.getParent() != null) Files.createDirectories(p.getParent());
                Files.write(p, e.getValue());
            }
        }
    }
}
