import org.gradle.api.internal.HasConvention
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.*
import java.nio.file.*
import java.util.concurrent.*
import java.util.stream.Collectors

val commitHash by lazy {
  val process: Process = Runtime.getRuntime().exec("git rev-parse --short HEAD")
  process.waitFor()
  val output = process.inputStream.use {
    it.bufferedReader().use(BufferedReader::readText)
  }
  process.destroy()
  output.trim()
}

val isCI = !System.getenv("CI").isNullOrBlank()

val pluginShortVersion = "1.0"
val packageName = "com.bulenkov.darcula"
val kotlinVersion: String by extra
val pluginCalculatedVersion = if (isCI) "$pluginShortVersion-$commitHash" else pluginShortVersion

group = packageName
version = pluginCalculatedVersion

buildscript {
  var kotlinVersion: String by extra
  kotlinVersion = "1.2.31"

  repositories {
    mavenCentral()
  }

  dependencies {
    classpath(kotlin("gradle-plugin", kotlinVersion))
  }
}

plugins {
  java
  kotlin("jvm") version "1.2.31"
}

apply {
  plugin("kotlin")
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

val SourceSet.kotlin
  get() = (this as HasConvention).convention.getPlugin(KotlinSourceSet::class.java).kotlin

java.sourceSets {
  "main" {
    java.setSrcDirs(listOf("src"))
    kotlin.setSrcDirs(listOf("src"))
    resources.setSrcDirs(listOf("res"))
  }

  "test" {
    java.setSrcDirs(listOf("test"))
    kotlin.setSrcDirs(listOf("test"))
  }
}

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  compile(kotlin("stdlib-jdk8", kotlinVersion))
  compile(files(*File("libs").listFiles()))
  testCompile("junit", "junit", version = "4.12")
  testCompile(kotlin("test-junit", kotlinVersion))
}

