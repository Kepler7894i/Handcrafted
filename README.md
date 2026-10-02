# Handcrafted (Minecraft [version] port)

This is an updated copy of **Handcrafted**, the furniture mod by Terrarium
(original source: <https://github.com/terrarium-earth/Handcrafted>, CurseForge: <https://www.curseforge.com/minecraft/mc-mods/handcrafted>).
The original mod stopped being updated and no longer works on current Minecraft, so this repository
recreates it for **Minecraft [version]** on **Fabric** and **NeoForge**. All credit for the mod, its design and its
assets goes to the original authors; this repository only ports the code and build to the new game version.

Not affiliated with or endorsed by Terrarium.

> **`[version]`** in this README stands for the Minecraft version this branch targets. It is set in one place (`minecraftVersion` in
> [gradle.properties](gradle.properties)) and everything is built and named from it: jar names (`handcrafted-<loader>-[version]-<mod version>.jar`),
> the mod metadata, release tags and release notes. See [Targeting another Minecraft version](#targeting-another-minecraft-version).

## Download and install

Prebuilt jars are on the [Releases page](../../releases): `handcrafted-fabric-[version]-*.jar` and `handcrafted-neoforge-[version]-*.jar`.
You also need [ResourcefulLib](https://www.curseforge.com/minecraft/mc-mods/resourceful-lib) (and Fabric API on Fabric); the install scripts below fetch both for you.

**The jars contain only code and data. They do not contain the mod’s textures, models, sounds or language files**, because that
artwork is All Rights Reserved by the original authors and cannot be redistributed here. Instead, after placing the jar in your
`mods` folder, run the asset installer once (needs the Java version Minecraft requires, currently Java 25 or newer):

```
java tools/AssetInstaller.java <path to the handcrafted jar, or your mods folder>
```

Or simply run the [install script](#install-scripts) for your OS, which does this for you.

(Download `AssetInstaller.java` from the `tools` folder of this repository.) It downloads the original Handcrafted 1.21.1 jar
from Modrinth to your own computer (checksum-verified, cached in `~/.cache/handcrafted-assets`), converts its artwork to the
format of the target Minecraft version and adds it to your Handcrafted jar. It also copies one texture (the skeleton trophy’s bow) from your own
Minecraft [version] install; pass `--mc-jar <path to the Minecraft jar>` if it is not in the default `.minecraft` folder. Re-run it after
updating the jar.

## Install scripts

The easiest way to install is the script for your OS. Each one installs Handcrafted **and its dependencies** into your `mods` folder and adds the artwork (see "Licensing" below):
- **ResourcefulLib**, downloaded for the right Minecraft version and loader. Always (re)installed, replacing any older copy.
- **Fabric API** (Fabric only), downloaded **only if your `mods` folder has no `fabric-api-*.jar` yet**; an existing Fabric API is never replaced.

Use `--no-deps` / `-NoDeps` to skip all dependency installs (then only Handcrafted itself is installed or replaced). Older `handcrafted-<loader>-*.jar` files are always replaced. Fabric Loader and NeoForge themselves are never touched.

| OS | Script |
| --- | --- |
| Windows (PowerShell) | `install-windows.ps1` |
| Linux | `install-linux.sh` |
| macOS | `install-macos.sh` |

They need Java 25+ on your `PATH` (the Java Minecraft itself uses). The Linux and macOS scripts also need `bash` and `curl`.

**Where the mod jar comes from.** If the script sits in a repository checkout (next to `gradlew`) it **compiles the mod first**
(code only, via `./gradlew :<loader>:build -PcodeOnly`). If it sits in a folder with a release jar (`handcrafted-<loader>-*.jar`) and `AssetInstaller.java`,
it **uses that jar**, so you can download a release's jar and script into one folder and run it there.

### Options

| Purpose | Windows (`install-windows.ps1`) | Linux / macOS (`install-linux.sh`, `install-macos.sh`) | Default |
| --- | --- | --- | --- |
| Folder to install into | `-ModsDir "<folder>"` | `--mods-dir <folder>` | Windows `%APPDATA%\.minecraft\mods`; Linux `~/.minecraft/mods`; macOS `~/Library/Application Support/minecraft/mods` |
| Mod loader to install | `-Loader fabric` or `-Loader neoforge` | `--loader fabric` or `--loader neoforge` | `fabric` |
| Don't compile, use the jar already built in `<loader>/build/libs` | `-SkipBuild` | `--skip-build` | compile when run from a checkout |
| Don't install dependencies (ResourcefulLib, Fabric API) | `-NoDeps` | `--no-deps` | install them |
| Minecraft jar to take the skeleton-trophy bow texture from | `-McJar "<path to the Minecraft jar>"` | `--mc-jar <path>` | auto-detected in your `.minecraft/versions` folder |
| Show help | `Get-Help .\install-windows.ps1 -Full` | `--help` | |

Examples:

```powershell
.\install-windows.ps1                                   # Fabric, into %APPDATA%\.minecraft\mods
.\install-windows.ps1 -Loader neoforge -ModsDir "D:\mc\mods"
```

```bash
./install-linux.sh                                      # Fabric, into ~/.minecraft/mods
./install-linux.sh --loader neoforge --mods-dir /srv/minecraft/mods --skip-build
```

Close Minecraft (and any server using the folder) first; Windows won't let a running game's jar be replaced.

### Using the asset installer directly

The scripts call `tools/AssetInstaller.java` for you. You can also run it yourself on an existing code-only jar (or a folder containing
`handcrafted-fabric-*.jar` / `handcrafted-neoforge-*.jar`); it modifies the jar(s) in place:

```
java tools/AssetInstaller.java <handcrafted jar, or a folder containing them> [--original <original-handcrafted-1.21.1.jar>] [--mc-jar <Minecraft jar>] [--mc-version <mc>]
```

- `--original`: use this copy of the original Handcrafted 1.21.1 jar instead of downloading it from Modrinth (it is otherwise downloaded, checksum-verified, and cached in `~/.cache/handcrafted-assets`).
- `--mc-jar`: Minecraft jar to copy the bow texture from; otherwise your `.minecraft/versions` folders are searched.
- `--mc-version`: Minecraft version the jar targets; normally read from the jar name (`handcrafted-<loader>-<mc>-<version>.jar`).

## Releases and old Minecraft versions

Every push to `main` runs [.github/workflows/release.yml](.github/workflows/release.yml), which publishes a release whose tag is the Minecraft version
(the value of `minecraftVersion`): the code-only jars, a source snapshot (`handcrafted-<mc>-source.zip`) and the asset installer. If the build fails, the source
snapshot is still published. When `main` moves to a newer Minecraft version, the older release stays, so you can always download the latest
build for an older Minecraft version from its tag.

## Targeting another Minecraft version

The target version lives only in `gradle.properties` (`minecraftVersion`, plus the matching `fabricApiVersion`, `neoforgeVersion`, `resourcefulLibVersion` and `fabricLoaderVersion`).
The mod metadata (`fabric.mod.json`, `neoforge.mods.toml`), jar names, release tag/name/notes, the installers and the asset installer are all derived from it, nothing else hardcodes a version.
To retarget:

```
java tools/SetVersion.java <minecraft version>            # looks up and writes the matching dependency versions
java tools/SetVersion.java <minecraft version> --dry-run  # only shows what it would change
```

That updates `gradle.properties` only; porting the code to whatever the new Minecraft version changed is still manual. On the next push to `main`,
the release workflow publishes a release for the new version and leaves the older versions' releases in place.

## Building

`./gradlew build` (Gradle downloads the required JDK 25 automatically). Jars end up in `fabric/build/libs` and `neoforge/build/libs`.
A clone has no artwork, so the jars it builds are code-only; run the asset installer on them as described above. (`-PcodeOnly` forces a code-only build even if you have the artwork locally.)

## Licensing, and why this works the way it does

**The original licence.** Handcrafted uses the *Terrarium License v1* (see [LICENSE](LICENSE)). It splits the project in two:
- **Code** (Java sources, build scripts, `data/` files such as recipes, loot tables and tags, and similar) is under the **MIT licence**. Anyone may use, modify and redistribute it, as long as the copyright notice is kept.
- **Everything else** (textures, models, blockstates, sounds, language files, the icon, i.e. everything under `assets/`) is **All Rights Reserved**. It may not be copied or redistributed without the owners’ permission.

**What that means here.** This repository is a modified version of the MIT-licensed code, so I am free to publish it, and I have kept the
licence and added my own copyright line for my changes. I am *not* free to publish the artwork, and I do not have the owners’ permission to.
So the repository and its release jars contain **only code and data**. Nothing in this repository relicenses or claims ownership of the artwork;
it still belongs to Terrarium and the original Handcrafted artists.

**Why an asset installer.** A mod without its textures and models is invisible in game, so the artwork still has to get into the jar somehow.
`tools/AssetInstaller.java` does that on *your* computer: it downloads the original mod from its official Modrinth page (which the authors
publish for free), converts the artwork to the format of the target Minecraft version and adds it to your jar. The artwork goes from the authors’ own
download straight to you; it is never hosted or redistributed by this repository. The converted result is for your own use and should not be re-uploaded.

If Terrarium (the rights holder) wants this changed, e.g. wants the installer removed or gives permission to bundle the artwork, I will do so.

## Why this is a standalone repository and not a fork

I wish to give credit where due, and would happily contribute back to the original codebase if asked, but this project is created as a **standalone repository** on purpose:
- **No artwork in the history.** A GitHub fork carries the *entire* history and every branch of the original, which includes all of the
  all-rights-reserved artwork. A fork would therefore keep redistributing it from my account even though my own code does not. A standalone repository
  starts from a clean tree that contains only what the licence lets me publish.
- **Clear ownership.** The code here is a port with a new build system (Architectury replaced by Fabric Loom and ModDevGradle) and the current Minecraft APIs, so it has
  diverged completely from the original branches. It is not meant to be merged back, a) it would not function, and b) a fork would wrongly suggest
  intention to use unlicensed components of that project.
- **No accidental upstream actions.** Forks are tied to the original network (pull requests, notifications, CI workflows). A standalone repository can’t
  accidentally send anything to, or run anything on behalf of, the original project.

Credit is still given where it is due: see the top of this README, the copyright lines in [LICENSE](LICENSE), and the original source at
<https://github.com/terrarium-earth/Handcrafted>.
