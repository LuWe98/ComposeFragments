plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
 //id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.welu.composefragments.example"
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.welu.composefragments"

        minSdk = Versions.minSdk
        targetSdk = Versions.compileSdk

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Versions.javaCompatibility
        targetCompatibility = Versions.javaCompatibility
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompilerVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    //Important for SaveArgs in Kotlin
//    sourceSets {
//        getByName("main").kotlin.srcDirs("build/generated/source/navigation-args")
//    }
}

dependencies {
    implementation(project(":compose-fragments"))

    //Core Android
    implementation("androidx.core:core-ktx:${Versions.androidxCoreVersion}")
    implementation("androidx.appcompat:appcompat:${Versions.androidxAppCompatVersion}")
    implementation("com.google.android.material:material:${Versions.androidxMaterialVersion}")


    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:${Versions.androidxLifecycleVersion}")


    //Compose
    implementation(platform("androidx.compose:compose-bom:${Versions.composeBomVersion}"))
    implementation("androidx.activity:activity-compose")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-viewbinding")


    //Fragments
    implementation("androidx.fragment:fragment-ktx:${Versions.fragmentVersion}")


    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}")
//    implementation("androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigationVersion}")


    //Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:${Versions.composeBomVersion}"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}