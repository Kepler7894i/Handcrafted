plugins {
    id("net.neoforged.moddev")
}

val minecraftVersion: String by project
val neoforgeVersion: String by project
val resourcefulLibVersion: String by project

sourceSets.main {
    java.srcDir("../common/src/main/java")
    resources.srcDir("../common/src/main/resources")
    resources.srcDir("../common/src/main/generated/resources")
}

neoForge {
    version = neoforgeVersion

    runs {
        create("client") { client() }
        create("server") { server() }
    }

    mods {
        create("handcrafted") { sourceSet(sourceSets.main.get()) }
    }
}

dependencies {
    implementation("com.teamresourceful.resourcefullib:resourcefullib-neoforge-$minecraftVersion:$resourcefulLibVersion")
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
    filesMatching("META-INF/neoforge.mods.toml") {
        expand("version" to project.version, "minecraft_version" to minecraftVersion, "resourceful_lib_version" to resourcefulLibVersion)
    }
}
