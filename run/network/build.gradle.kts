plugins {
    alias(libs.plugins.runiquecourse.android.library)
}

android {
    namespace = "dev.rikoapp.run.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}