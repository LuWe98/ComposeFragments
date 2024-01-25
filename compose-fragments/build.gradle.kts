plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("maven-publish")
}

android {
    namespace = "com.welu.composefragments"
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = Versions.javaCompatibility
        targetCompatibility = Versions.javaCompatibility
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompilerVersion
    }

    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
}

dependencies {
    implementation("com.google.android.material:material:${Versions.androidxMaterialVersion}")

    //Compose
    implementation(platform("androidx.compose:compose-bom:${Versions.composeBomVersion}"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")

    //Fragments
    implementation("androidx.fragment:fragment-ktx:${Versions.fragmentVersion}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.LuWe98"
            artifactId = "core"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}