plugins {
    alias(libs.plugins.runiquecourse.jvm.library)
    alias(libs.plugins.runiquecourse.jvm.junit5)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    implementation(projects.core.domain)
    implementation(projects.core.connectivity.domain)

    testImplementation(projects.core.test)
}