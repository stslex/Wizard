plugins {
    alias(libs.plugins.convention.kmp.library.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:core"))
            implementation(project(":core:network"))
        }
        androidMain.dependencies {
            api(libs.coil.compose)
        }
        iosMain.dependencies {
            // TODO research TLS PROBLEM
            implementation(libs.ktor.client.darwin)
        }
    }
}
