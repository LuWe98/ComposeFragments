import org.gradle.api.JavaVersion

object Versions {

    const val compileSdk = 34
    const val minSdk = 24

    val javaCompatibility = JavaVersion.VERSION_11
    const val jvmTarget = "11"

    const val kotlinVersion = "1.9.10"
    const val androidLibraryVersion = "8.2.1"

    const val androidxCoreVersion = "1.12.0"
    const val androidxMaterialVersion = "1.11.0"
    const val androidxLifecycleVersion = "2.7.0"
    const val androidxAppCompatVersion = "1.6.1"

    const val composeCompilerVersion = "1.5.3"
    const val composeBomVersion = "2023.08.00"

    const val fragmentVersion = "1.6.2"
    const val navigationVersion = "2.7.6"
}