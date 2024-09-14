plugins {
    alias(libs.plugins.runiquecourse.android.feature.ui)
}

android {
    namespace = "dev.rikoapp.auth.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}