plugins {
    alias(libs.plugins.runiquecourse.android.library)
}

android {
    namespace = "dev.rikoapp.analytics.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.analytics.domain)
}