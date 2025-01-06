plugins {
    alias(libs.plugins.runiquecourse.android.library)
    alias(libs.plugins.runiquecourse.jvm.ktor)
}

android {
    namespace = "dev.rikoapp.auth.data"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}