import org.gradle.api.JavaVersion

object Versions {

    const val compileSdk = 34
    const val minSdk = 24

    val javaCompatibility = JavaVersion.VERSION_1_8
    const val jvmTarget = "1.8"

    const val kotlinVersion = "1.9.10"
    const val androidLibraryVersion = "8.2.2"

    const val androidxCoreVersion = "1.13.1"
    const val androidxMaterialVersion = "1.12.0"
    const val androidxLifecycleVersion = "2.7.0"
    const val androidxAppCompatVersion = "1.6.1"

    const val composeCompilerVersion = "1.5.3"
    const val composeBomVersion = "2024.05.00"

    const val fragmentVersion = "1.7.0"
    const val navigationVersion = "2.7.7"
}