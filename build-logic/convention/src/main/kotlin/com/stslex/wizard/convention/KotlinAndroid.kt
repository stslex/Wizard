package com.stslex.wizard.convention

import AppExt.APP_PREFIX
import AppExt.findVersionInt
import AppExt.libs
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKotlinAndroid(
    extension: CommonExtension<*, *, *, *, *, *>,
    isApp: Boolean = false
) = extension.apply {

    //get module name from module path
    val dropValue = if (isApp) 2 else 1
    val moduleName = path.split(":")
        .drop(dropValue)
        .joinToString(".")
    namespace = if (moduleName.isNotEmpty()) "$APP_PREFIX.$moduleName" else APP_PREFIX

    println("android namespace: $namespace")

    compileSdk = libs.findVersionInt("compileSdk")

    defaultConfig {
        minSdk = libs.findVersionInt("minSdk")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        buildConfig = true
    }

    dependencies {
        "implementation"(libs.findLibrary("koin-core").get())
        "implementation"(libs.findLibrary("koin-annotations").get())
        "implementation"(libs.findLibrary("koin-android").get())
        "implementation"(libs.findLibrary("coroutine-core").get())
        "implementation"(libs.findLibrary("coroutine-android").get())
    }
}