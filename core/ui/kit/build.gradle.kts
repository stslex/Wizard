plugins {
    alias(libs.plugins.convention.kmp.library.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:core"))
            implementation(project(":core:network:api"))
            implementation(libs.kotlinx.datetime)
            implementation(libs.bundles.coil)
            implementation(libs.bundles.ktor)
            implementation(libs.slf4j.simple)
        }
        iosMain.dependencies {
            api(libs.ktor.client.darwin)
        }
        androidMain.dependencies {
            api(libs.ktor.client.android)
        }
    }
}
