plugins {
    alias(libs.plugins.runiquecourse.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}