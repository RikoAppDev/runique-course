plugins {
    alias(libs.plugins.runiquecourse.android.library)
}

android {
    namespace = "dev.rikoapp.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)

    implementation(projects.core.domain)
}