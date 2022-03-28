import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "me.daan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.web3j:core:4.9.0")
    implementation("org.web3j:abi:4.9.0")
    implementation("org.web3j:contracts:4.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    implementation("io.github.cdimascio:dotenv-java:2.2.0")


    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.16.0")
    implementation("org.apache.logging.log4j:log4j-api:2.16.0")
    implementation("org.apache.logging.log4j:log4j-core:2.16.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}