package com.stslex.wizard.convention

import AppExt.findPluginId
import AppExt.libs
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension,
) = extension.apply {

    pluginManager.apply(libs.findPluginId("ksp"))

    jvmToolchain(17)

    // targets
    androidTarget()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    applyDefaultHierarchyTemplate()

    dependencies {
        add("kspCommonMainMetadata", libs.findLibrary("koin-ksp-compiler").get())
    }

    sourceSets.apply {

        commonMain {
            kotlin.srcDirs(
                "build/generated/ksp/metadata/commonMain/kotlin",
                "build/generated/ksp/commonMain/kotlin"
            )
            dependencies {
                implementation(libs.findLibrary("koin-core").get())
                implementation(libs.findLibrary("koin-annotations").get())
                implementation(libs.findLibrary("coroutine-core").get())
                implementation(libs.findLibrary("kotlinx-serialization-json").get())
            }
        }

        androidMain {
            dependencies {
                implementation(libs.findLibrary("koin-android").get())
                implementation(libs.findLibrary("coroutine-core").get())
                implementation(libs.findLibrary("coroutine-android").get())
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

    //applying the Cocoapods Configuration we made
    (this as ExtensionAware).extensions.configure<CocoapodsExtension>(::configureKotlinCocoapods)

    tasks.withType(KotlinCompile::class.java).all {
        if (name != "kspCommonMainKotlinMetadata") dependsOn("kspCommonMainKotlinMetadata")
    }
}
