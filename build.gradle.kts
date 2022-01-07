plugins {
    java
    `java-library`
}

group = "cz.tefek"
version = "0.0.1-alpha.0"
description = "A Java based UI layout library pluggable into any renderer."

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Wrapper> {
    distributionType = Wrapper.DistributionType.ALL
    gradleVersion = "7.2"
}

repositories {
    mavenLocal()
    mavenCentral()

    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/493msi/plutoengine/")
        credentials {
            username = "493msi"
            password = System.getenv("GITHUB_PACKAGES_KEY")
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

dependencies {
    implementation("org.jetbrains", "annotations", "20.1.0")

    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.12.3")
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", "2.12.3")

    implementation("org.joml", "joml", "1.10.2")
    /*
    implementation("org.slf4j", "slf4j-api", "1.8.0-beta4")
    implementation("org.slf4j", "slf4j-simple", "1.8.0-beta4")
    implementation("org.apache.logging.log4j", "log4j-core", "2.16.0")
    implementation("org.apache.logging.log4j", "log4j-api", "2.16.0")
    implementation("org.apache.logging.log4j", "log4j-slf4j-impl", "2.16.0")
    */

    implementation("org.apache.commons", "commons-lang3", "3.11")
}