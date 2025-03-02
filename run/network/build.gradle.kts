plugins {
    alias(libs.plugins.runiquecourse.android.library)
    alias(libs.plugins.runiquecourse.jvm.ktor)
}

android {
    namespace = "dev.rikoapp.run.network"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.data)
}