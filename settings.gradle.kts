rootProject.name = "Wizard"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":composeApp")

include(":core:core")
include(":core:network")
include(":core:ui")
include(":core:database")
include(":feature:film_feed")
include(":feature:film")
include(":feature:profile")
include(":feature:match_feed")
