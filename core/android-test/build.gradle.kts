plugins {
    alias(libs.plugins.runiquecourse.android.library)
    alias(libs.plugins.runiquecourse.android.junit5)
}

android {
    namespace = "dev.rikoapp.core.android_test"
}

dependencies {
    implementation(projects.auth.data)
    implementation(projects.core.domain)
    api(projects.core.test)

    implementation(libs.ktor.client.mock)
    implementation(libs.bundles.ktor)
    implementation(libs.coroutines.test)
}