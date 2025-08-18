plugins {
    alias(libs.plugins.runiquecourse.android.dynamic.feature)
}
android {
    namespace = "dev.rikoapp.analytics.analytics_feature"
}

dependencies {
    implementation(project(":app"))
    implementation(libs.androidx.navigation.compose)

    api(projects.analytics.presentation)
    implementation(projects.analytics.domain)
    implementation(projects.analytics.data)
    implementation(projects.core.database)
}