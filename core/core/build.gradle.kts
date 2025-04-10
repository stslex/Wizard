plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.mockmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kermit)
            implementation(kotlin("test"))
            implementation(libs.coroutine.test)
        }
    }
}
