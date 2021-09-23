import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.publish.maven.MavenPublication as MavenPublication

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    java
    `maven-publish`
    signing
}

group = "top.xcore"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
    mavenLocal()
    mavenCentral()
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    api("top.xcore","xdata-core","1.0.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "16"
    }
}

tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.findByName("javadoc"))
}

tasks.register<Jar>("sourceJar") {
    archiveClassifier.set("sources")
    from(sourceSets.asMap["main"]!!.java)
}


tasks.withType<Jar>() {
    println(this.archiveFile.get())
    if ((this.archiveFile.get().toString()).contains("-plain")) {
        println("classifier : " + this.classifier);
        this.archiveClassifier.set("")
    }
}

artifacts {
    //archives(tasks.findByName("jar")!!)
    //archives(tasks.findByName("javadocJar")!!)
    //archives(tasks.findByName("sourceJar")!!)
}




publishing {
    publications {
        create<MavenPublication>("maven") {
                artifact(tasks["jar"])
                artifact(tasks["javadocJar"])
                artifact(tasks["sourceJar"])
                pom {
                    name.set("xdata-core")
                    url.set("https://www.xcore.top")
                    groupId = "top.xcore"
                    artifactId = "springboot.adapter"
                    version = "0.0.1"
                    packaging = "jar"
                    description.set("An adapter to integrate xdata with SpringBoot")
                    scm {
                        connection.set("scm:git:htts://github.com/j383575602/xdata-spring-adapter.git")
                        developerConnection.set("scm:git:htts://github.com/j383575602/xdata-spring-adapter.git")
                        url.set("htts://github.com/j383575602/xdata-spring-adapter")
                    }
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    developers {
                        developer {
                            id.set("xcore")
                            name.set("xcore")
                            email.set("j383575602@qq.com")
                        }
                    }

                }


        }
    }

    repositories {
        val snap = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
        var release = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
        maven {
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snap else release)
            credentials {
                username = project.properties["ossrhUsername"] as String
                password = project.properties["ossrhPassword"] as String
            }
        }

    }
}

signing {
    sign(publishing.publications.findByName("maven"))
}
