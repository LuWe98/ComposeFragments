buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.3.2")
        //        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}")

    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

plugins {
    id("com.android.application") version Versions.androidLibraryVersion apply false
    id("com.android.library") version Versions.androidLibraryVersion apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlinVersion apply false
    id("org.jetbrains.kotlin.jvm") version Versions.kotlinVersion apply false
}