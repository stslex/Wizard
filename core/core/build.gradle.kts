plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinCocoapods)
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

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file(project.rootProject.projectDir.path + "/iosApp/CorePodfile")
        framework {
            baseName = "core"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kermit)
            implementation(compose.runtime)
            implementation(compose.foundation)
            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.kotlinx.collections.immutable)
            api(libs.coroutines.core)
        }
        androidMain.dependencies {
            api(libs.coroutines.android)
            api(libs.koin.android)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.stslex.core.core"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
