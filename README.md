# Handcrafted (Minecraft 26.2 port)

This is an updated copy of **Handcrafted**, the furniture mod by Terrarium
(original source: <https://github.com/terrarium-earth/Handcrafted>, CurseForge: <https://www.curseforge.com/minecraft/mc-mods/handcrafted>).
The original mod stopped being updated and no longer works on current Minecraft, so this repository
recreates it for **Minecraft 26.2** on **Fabric** and **NeoForge**. All credit for the mod, its design and its
assets goes to the original authors; this repository only ports the code and build to the new game version.

Not affiliated with or endorsed by Terrarium.

## Download and install

Prebuilt jars are on the [Releases page](../../releases): `handcrafted-fabric-26.2-*.jar` and `handcrafted-neoforge-26.2-*.jar`.
You also need [ResourcefulLib](https://www.curseforge.com/minecraft/mc-mods/resourceful-lib) 5.x (and Fabric API on Fabric).

**The jars contain only code and data. They do not contain the mod’s textures, models, sounds or language files**, because that
artwork is All Rights Reserved by the original authors and cannot be redistributed here. Instead, after placing the jar in your
`mods` folder, run the asset installer once (needs Java 25 or newer, which Minecraft 26.x already requires):

```
java tools/AssetInstaller.java <path to the handcrafted jar, or your mods folder>
```

(Download `AssetInstaller.java` from the `tools` folder of this repository.) It downloads the original Handcrafted 1.21.1 jar
from Modrinth to your own computer (checksum-verified, cached in `~/.cache/handcrafted-assets`), converts its artwork to the
Minecraft 26.x format and adds it to your Handcrafted jar. It also copies one texture (the skeleton trophy’s bow) from your own
Minecraft 26.2 install; pass `--mc-jar <path to 26.2.jar>` if it is not in the default `.minecraft` folder. Re-run it after
updating the jar.

## Building

`./gradlew build` (needs JDK 26 and JDK 25; Gradle downloads JDK 25 automatically). Jars end up in `fabric/build/libs` and `neoforge/build/libs`.
A clone has no artwork, so the jars it builds are code-only; run the asset installer on them as described above. (`-PcodeOnly` forces a code-only build even if you have the artwork locally.)
On Windows, `install-fabric.ps1` builds and installs the Fabric version plus ResourcefulLib into a mods folder.

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
publish for free), converts the artwork to the Minecraft 26.x format and adds it to your jar. The artwork goes from the authors’ own
download straight to you; it is never hosted or redistributed by this repository. The converted result is for your own use and should not be re-uploaded.

If Terrarium (the rights holder) wants this changed, e.g. wants the installer removed or gives permission to bundle the artwork, I will do so.

## Why this is a standalone repository and not a fork

This project began as a fork of the original, but I recreated it as a **standalone repository** on purpose:
- **No artwork in the history.** A GitHub fork carries the *entire* history and every branch of the original, which includes all of the
  all-rights-reserved artwork. A fork would therefore keep redistributing it from my account even though my own code does not. A standalone repository
  starts from a clean tree that contains only what the licence lets me publish.
- **Clear ownership.** The code here is a port with a new build system (Architectury replaced by Fabric Loom and ModDevGradle) and 26.x APIs, so it has
  diverged completely from the original branches. It is not meant to be merged back, and a fork would wrongly suggest it is an official branch of the project.
- **No accidental upstream actions.** Forks are tied to the original network (pull requests, notifications, CI workflows). A standalone repository can’t
  accidentally send anything to, or run anything on behalf of, the original project.

Credit is still given where it is due: see the top of this README, the copyright lines in [LICENSE](LICENSE), and the original source at
<https://github.com/terrarium-earth/Handcrafted>.

---

## Original README

# Handcrafted

To add this library to your project, do the following:

Kotlin DSL:
```kotlin
repositories {
    maven(url = "https://maven.teamresourceful.com/repository/maven-public/")
}

dependencies {
    modImplementation(group = "earth.terrarium.handcrafted", name = "handcrafted-$modLoader-$minecraftVersion", version = handcraftedVersion)
}
```

Groovy DSL:
```groovy
repositories {
    maven {
        url "https://maven.teamresourceful.com/repository/maven-public/"
    }
}

dependencies {
    modImplementation group: "earth.terrarium.handcrafted", name: "handcrafted-$modLoader-$minecraftVersion", version: handcraftedVersion
}
```
