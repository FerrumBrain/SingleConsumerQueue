plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.1")
    implementation("org.jetbrains.kotlinx:atomicfu:0.20.2")
    testImplementation("org.jetbrains.kotlinx:lincheck:2.34")
}

kotlin {
    jvmToolchain(21)
}