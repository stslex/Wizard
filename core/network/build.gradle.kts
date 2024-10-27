import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.github.gmazzo.gradle.plugins.BuildConfigExtension
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.serialization)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

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
    namespace = "com.stslex.wizard.core.network"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}


fun BuildConfigExtension.setLocalProperty(dir: Project) {
    val localProperties = gradleLocalProperties(dir.projectDir, providers)

    buildStringField(localProperties, "KINOPOISK_API_KEY")
    buildStringField(localProperties, "SERVER_HOST")
    buildStringField(localProperties, "SERVER_API_VERSION")
    buildStringField(localProperties, "SERVER_PORT")
    buildStringField(localProperties, "SERVER_API_KEY")
}

fun BuildConfigExtension.buildStringField(localProperties: Properties, name: String) {
    buildConfigField("String", name, localProperties.getString(name))
}

fun Properties.getString(key: String): String {
    return getProperty(key) ?: throw IllegalStateException("$key should be initialised")
}
