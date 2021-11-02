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
    //implementation(fileTree("./../libs"))
    implementation("top.xcore","xdata-core","1.0.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}