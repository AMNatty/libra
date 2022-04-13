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
    gradleVersion = "7.4.2"
}

repositories {
    mavenLocal()
    mavenCentral()

    maven {
        name = "Vega"
        url = uri("https://vega.botdiril.com/")
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

dependencies {
    implementation("org.jetbrains", "annotations", "23.0.0")

    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.12.3")
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", "2.12.3")

    if (project.parent != null && project.parent!!.ext.has("isPlutoBuild"))
        api(project(":plutoengine:plutolib"))
    else
        api("org.plutoengine", "plutolib", "22.1.0.0-alpha.0")

    implementation("org.apache.commons", "commons-lang3", "3.11")
}