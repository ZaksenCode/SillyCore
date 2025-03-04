plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"

    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("maven-publish")
}

group = project.property("group") as String
version = project.property("version") as String

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc"
    }
}

dependencies {
    testImplementation(kotlin("test"))

    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")

    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    api("com.charleskorn.kaml:kaml:0.66.0")

    api("org.incendo:cloud-core:${project.property("cloud_version")}")
    api("org.incendo:cloud-kotlin-coroutines-annotations:${project.property("cloud_version")}")
    api("org.incendo:cloud-paper:${project.property("cloud_version_paper")}")
}

tasks.test {
    useJUnitPlatform()
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf(
        "version" to version,
        "plugin_name" to project.property("plugin_name"),
        "plugin_main_class" to project.property("plugin_main_class"),
        "plugin_api_version" to project.property("plugin_api_version")
    )
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("paper-plugin.yml") {
        expand(props)
    }
}

kotlin {
    jvmToolchain(21)
}

publishing {
    repositories {
        maven(project.property("repo_url") as String) {
            name = "GitHubPackages"
            credentials {
                username = (project.findProperty("gpr.user") ?: System.getenv("GITHUB_USERNAME")) as String
                password = (project.findProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")) as String
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            artifactId = project.property("artifact_id") as String
            version = project.version as String
            from(components["java"])
        }
    }
}