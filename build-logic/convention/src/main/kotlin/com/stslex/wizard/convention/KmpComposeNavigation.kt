package com.stslex.wizard.convention

import AppExt.libs
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension

fun Project.configureKMPComposeNavigation(
    extension: KotlinMultiplatformExtension,
) = extension.apply {
    sourceSets.apply {
        commonMain.dependencies {
            implementation(libs.findLibrary("decompose").get())
            implementation(libs.findLibrary("decompose.extensions").get())
            implementation(libs.findLibrary("essenty.lifecycle").get())
            implementation(libs.findLibrary("essenty.stateKeeper").get())
            implementation(libs.findLibrary("essenty.backHandler").get())
        }
    }
    (this as ExtensionAware).extensions.configure<CocoapodsExtension> {
        framework {
            export(libs.findLibrary("decompose").get())
            export(libs.findLibrary("essenty.lifecycle").get())
            export(libs.findLibrary("essenty.stateKeeper").get())
            export(libs.findLibrary("parcelize.darwin").get())
        }
    }
}