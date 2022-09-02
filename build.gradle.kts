plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("java-library")
    id("maven-publish")
    id("signing")
}

val versionName = "2.1-SNAPSHOT"

group = "com.avonfitzgerald"
version = versionName

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")

    api("io.arrow-kt:arrow-core:1.1.2")
    api("io.ktor:ktor-client-cio:2.1.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
}

tasks.test {
    useJUnitPlatform()
}

java {
    withJavadocJar()
    withSourcesJar()
}

val sonatypeUsername : String? = System.getenv("SONATYPE_USERNAME")
val sonatypePassword : String? = System.getenv("SONATYPE_PASSWORD")
val gpgPrivateKey: String? = System.getenv("GPG_PRIVATE_KEY")
val gpgPassword: String? = System.getenv("GPG_PASSWORD")

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.avonfitzgerald"
            artifactId = "infinitelive"
            version = versionName

            from(components["java"])

            pom {
                name.set("InfiniteLiveKt")
                description.set("Kotlin library for the Infinite Flight Live API")
                url.set("https://github.com/A-FitzGerald/InfiniteLiveKt")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                issueManagement {
                    system.set("Github")
                    url.set("https://github.com/A-FitzGerald/InfiniteLiveKt/issues")
                }
                scm {
                    connection.set("https://github.com/A-FitzGerald/InfiniteLiveKt.git")
                    url.set("https://github.com/A-FitzGerald/InfiniteLiveKt")
                }
                developers {
                    developer {
                        name.set("Avon FitzGerald")
                        email.set("contact@avonfitzgerald.com")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "InfiniteLiveKt"
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            url = if(version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

            credentials {
                username = sonatypeUsername
                password = sonatypePassword
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        gpgPrivateKey,
        gpgPassword
    )
    sign(publishing.publications["maven"])
}

tasks.javadoc {
    if(JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}
