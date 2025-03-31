plugins {
    alias(libs.plugins.convention.kmp.application)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}

kotlin {
    sourceSets.apply {
        commonMain.dependencies {
            implementation(project(":core:core"))
            implementation(project(":core:network:client"))
            implementation(project(":core:network:api"))
            implementation(project(":core:database"))
            implementation(project(":core:ui:kit"))
            implementation(project(":core:ui:mvi"))
            implementation(project(":core:navigation"))

            implementation(project(":feature:film_feed"))
            implementation(project(":feature:film"))
            implementation(project(":feature:profile"))
            implementation(project(":feature:match_feed"))
            implementation(project(":feature:auth"))
            implementation(project(":feature:follower"))
            implementation(project(":feature:favourite"))
            implementation(project(":feature:settings"))
            implementation(project(":feature:match"))
        }
    }
}