// FileName: build.gradle.kts (Project: ToDo)
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.ksp) apply false
    // Add the Hilt plugin alias here
    alias(libs.plugins.hilt) apply false
}