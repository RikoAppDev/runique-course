plugins {
    alias(libs.plugins.runiquecourse.jvm.library)
    alias(libs.plugins.runiquecourse.jvm.junit5)
}

dependencies {
    implementation(projects.core.domain)
}