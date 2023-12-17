import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.github.gmazzo.gradle.plugins.BuildConfigExtension

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.buildConfig)
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
        podfile = project.file(project.rootProject.projectDir.path + "/iosApp/CoreNetworkPodfile")
        framework {
            baseName = "coreNetwork"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:core"))
            implementation(project(":core:database"))
            implementation(libs.bundles.ktor)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.slf4j.simple)

            implementation(libs.kamel)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        buildConfig {
            setLocalProperty(project.rootProject)
        }
    }
}

android {
    namespace = "com.stslex.core.network"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}


fun BuildConfigExtension.setLocalProperty(dir: Project) {
    val key = gradleLocalProperties(dir.projectDir)["KINOPOISK_API_KEY"]
        ?.toString()
        ?: throw IllegalStateException("KINOPOISK_API_KEY should be initialised")
    buildConfigField("String", "KINOPOISK_API_KEY", key)
}
