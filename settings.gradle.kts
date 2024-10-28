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

include(":core:core")
include(":core:network")
include(":core:ui")
include(":core:database")
include(":core:navigation")

include(":feature:film_feed")
include(":feature:film")
include(":feature:profile")
include(":feature:match_feed")
include(":feature:auth")
include(":feature:follower")
include(":feature:favourite")
include(":feature:settings")
include(":feature:match")
