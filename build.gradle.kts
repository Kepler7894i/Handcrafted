plugins {
    java
    id("net.fabricmc.fabric-loom") version "1.18.2" apply false
    id("net.neoforged.moddev") version "2.0.148" apply false
}

configure(subprojects.filter { it.name != "common" }) {
    // Minecraft 26.x needs Java 25.
    apply(plugin = "java")

    val minecraftVersion: String by project
    val modLoader = project.name

    group = rootProject.property("group") as String
    version = rootProject.property("version") as String

    base {
        archivesName.set("handcrafted-$modLoader-$minecraftVersion")
    }

    repositories {
        mavenCentral()
        maven(url = "https://maven.fabricmc.net/")
        maven(url = "https://maven.neoforged.net/releases/")
        maven(url = "https://maven.teamresourceful.com/repository/maven-public/")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(25))
        withSourcesJar()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(25)
        options.compilerArgs.addAll(listOf("-Xmaxerrs", "2000"))
    }
}
