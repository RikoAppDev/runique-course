plugins {
    alias(libs.plugins.runiquecourse.android.library.compose)
}

android {
    namespace = "dev.rikoapp.core.presentation.designsystem_wear"

    defaultConfig {
        minSdk = libs.versions.wearMinSdkVersion.get().toInt()
    }
}

dependencies {
    api(projects.core.presentation.designsystem)

    implementation(libs.androidx.wear.compose.material)
}