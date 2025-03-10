plugins {
    kotlin("jvm") version "2.1.10"
}

group = "arc.discord"
version = "1.0-SNAPSHOT"

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        implementation(rootProject.libs.koin)
    }

}
kotlin {
    jvmToolchain(21)
}