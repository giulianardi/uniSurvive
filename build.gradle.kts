plugins {
    id("java")
    id ("application")
}

group = "it.unicam.cs.mpgc.rpg130675"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.formdev:flatlaf:3.4")
    implementation("com.formdev:flatlaf-intellij-themes:3.4")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("it.unicam.cs.mpgc.rpg130675.app.Applicazione")
}