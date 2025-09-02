plugins {
    alias(libs.plugins.runiquecourse.android.library)
}

android {
    namespace = "dev.rikoapp.wear.run.data"

    defaultConfig {
        minSdk = libs.versions.wearMinSdkVersion.get().toInt()
    }
}

dependencies {
    implementation(libs.androidx.health.services.client)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.wear.run.domain)
}