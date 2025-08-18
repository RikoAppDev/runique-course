plugins {
    alias(libs.plugins.runiquecourse.android.feature.ui)
}

android {
    namespace = "dev.rikoapp.analytics.presentation"
}

dependencies {
    implementation(projects.analytics.domain)
}