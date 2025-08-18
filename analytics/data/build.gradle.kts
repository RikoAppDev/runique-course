plugins {
    alias(libs.plugins.runiquecourse.android.library)
    alias(libs.plugins.runiquecourse.android.room)
}

android {
    namespace = "dev.rikoapp.analytics.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.bundles.koin)

    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.analytics.domain)
}