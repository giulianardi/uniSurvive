plugins {
    id("java")
    id ("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "it.unicam.cs.mpgc.rpg130675"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

javafx {
    version = "25.0.3"
    modules = listOf("javafx.controls")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.formdev:flatlaf:3.4")
    implementation("com.formdev:flatlaf-intellij-themes:3.4")
}

application {
    mainClass.set("it.unicam.cs.mpgc.rpg130675.app.Applicazione")
}