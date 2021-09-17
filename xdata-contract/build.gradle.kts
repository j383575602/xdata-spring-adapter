plugins {
    kotlin("jvm")
    java
}

group = "com.demo.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation(fileTree("./../libs"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}