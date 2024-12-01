plugins {
    alias(libs.plugins.convention.kmp.library.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:core"))
            implementation(project(":core:network:api"))
            implementation(libs.kotlinx.datetime)
        }
    }
}
