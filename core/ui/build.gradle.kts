import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ui"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        commonMain.dependencies {
            implementation(project(":core:core"))

            api(compose.runtime)
            api(compose.foundation)
            implementation(compose.material)
            api(compose.material3)
            @OptIn(ExperimentalComposeLibrary::class)
            api(compose.components.resources)
            api(libs.bundles.voyager)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            api(libs.compose.ui)
            api(libs.compose.ui.tooling.preview)
            api(libs.androidx.activity.compose)
            api(libs.koin.android)
            api(libs.koin.androidx.compose)
        }
    }
}

android {
    namespace = "com.stslex.core.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
