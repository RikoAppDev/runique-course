plugins {
    alias(libs.plugins.runiquecourse.android.library)
    alias(libs.plugins.runiquecourse.android.room)
}

android {
    namespace = "dev.rikoapp.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
}