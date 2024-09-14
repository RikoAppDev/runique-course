plugins {
    alias(libs.plugins.runiquecourse.android.feature.ui)
}

android {
    namespace = "dev.rikoapp.run.presentation"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.google.maps.android.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.run.domain)
}