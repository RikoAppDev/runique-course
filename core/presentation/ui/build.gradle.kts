plugins {
    alias(libs.plugins.runiquecourse.android.library.compose)
}

android {
    namespace = "dev.rikoapp.presentation.ui"
}

dependencies {
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
}