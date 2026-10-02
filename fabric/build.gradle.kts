plugins {
    id("net.fabricmc.fabric-loom")
}

val minecraftVersion: String by project
val fabricLoaderVersion: String by project
val fabricApiVersion: String by project
val resourcefulLibVersion: String by project

sourceSets.main {
    java.srcDir("../common/src/main/java")
    resources.srcDir("../common/src/main/resources")
    resources.srcDir("../common/src/main/generated/resources")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")

    implementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
    implementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion+$minecraftVersion")

    implementation("com.teamresourceful.resourcefullib:resourcefullib-fabric-$minecraftVersion:$resourcefulLibVersion")
}

tasks.processResources {
    // -PcodeOnly builds a jar without the (all-rights-reserved) artwork; see tools/AssetInstaller.java.
    if (project.hasProperty("codeOnly")) {
        exclude("assets/**", "icon.png")
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    inputs.property("version", project.version)
    inputs.property("minecraft_version", minecraftVersion)
    inputs.property("resourceful_lib_version", resourcefulLibVersion)
    filesMatching("fabric.mod.json") {
        expand("version" to project.version, "minecraft_version" to minecraftVersion, "resourceful_lib_version" to resourcefulLibVersion)
    }
}

