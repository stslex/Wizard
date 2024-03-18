import org.jetbrains.compose.ExperimentalComposeLibrary

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
        podfile = project.file(project.rootProject.projectDir.path + "/iosApp/CoreUiPodfile")
        framework {
            baseName = "coreUi"
        }
    }

    sourceSets {
        val desktopMain by getting

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        commonMain.dependencies {
            implementation(project(":core:core"))
            implementation(project(":core:network"))

            api(compose.runtime)
            api(compose.foundation)
            api(compose.material)
            api(compose.material3)
            @OptIn(ExperimentalComposeLibrary::class)
            api(compose.components.resources)
            api(libs.bundles.voyager)
            api(libs.kamel)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            api(libs.compose.ui)
            api(libs.compose.ui.tooling.preview)
            api(libs.androidx.activity.compose)
            api(libs.coil.compose)
        }
        iosMain.dependencies {
            // TODO research TLS PROBLEM
            implementation("io.ktor:ktor-client-darwin:2.3.2")
        }
        dependencies {
            debugApi(libs.compose.ui.tooling)
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions.freeCompilerArgs.addAll(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=$projectDir/build/compose/metrics",
    )
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions.freeCompilerArgs.addAll(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=$projectDir/build/compose/reports",
    )
}
