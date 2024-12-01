plugins {
    alias(libs.plugins.convention.kmp.library.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:core"))
            implementation(project(":core:ui:image"))
            implementation(libs.kotlinx.datetime)
        }
    }
}
