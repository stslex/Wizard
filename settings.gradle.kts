enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Wizard"

include(":iosApp")
include(":commonApp")

// Core
include(":core:core")
// Network
include(":core:network:client")
include(":core:network:api")
// UI
include(":core:ui:kit")
include(":core:ui:image")
// Other
include(":core:database")
include(":core:navigation")
// Features
include(":feature:film_feed")
include(":feature:film")
include(":feature:profile")
include(":feature:match_feed")
include(":feature:auth")
include(":feature:follower")
include(":feature:favourite")
include(":feature:settings")
include(":feature:match")
