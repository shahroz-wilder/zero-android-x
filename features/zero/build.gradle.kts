plugins {
    id("io.element.android-compose-library")
    id("kotlin-parcelize")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.zero"
    compileSdk = 34
}

dependencies {

//    implementation(projects.anvilannotations)
//    anvil(projects.anvilcodegen)
    implementation(projects.appconfig)
    implementation(projects.libraries.core)
    implementation(projects.libraries.androidutils)
//    implementation(projects.libraries.architecture)
    implementation(projects.libraries.designsystem)
//    implementation(projects.libraries.featureflag.api)
//    implementation(projects.libraries.matrix.api)
//    implementation(projects.libraries.testtags)
//    implementation(projects.libraries.uiStrings)

    implementation (libs.serialization.json)
/*    testImplementation(libs.test.junit)
    testImplementation(libs.androidx.compose.ui.test.junit)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.molecule.runtime)
    testImplementation(libs.test.robolectric)
    testImplementation(libs.test.truth)
    testImplementation(libs.test.turbine)
    testImplementation(projects.libraries.matrix.test)
    testImplementation(projects.libraries.featureflag.test)
    testImplementation(projects.tests.testutils)
    testReleaseImplementation(libs.androidx.compose.ui.test.manifest)*/
}
