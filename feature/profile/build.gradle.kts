plugins {
    alias(libs.plugins.convention.kmp.library.compose)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(project(":core:core"))
        implementation(project(":core:ui:kit"))
        implementation(project(":core:ui:image"))
        implementation(project(":core:ui:mvi"))
        implementation(project(":core:network:api"))
        implementation(project(":core:database"))
        implementation(project(":core:navigation"))
    }
}
