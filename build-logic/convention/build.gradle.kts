plugins {
    `kotlin-dsl`
}

group = "com.stslex.wizard.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlinLibraryMultiplatform") {
            id = "convention.kmp.library"
            implementationClass = "KMPLibraryConventionPlugin"
        }
        register("kotlinApplicationMultiplatform") {
            id = "convention.kmp.application"
            implementationClass = "KMPApplicationConventionPlugin"
        }
        register("kotlinLibraryComposeMultiplatform") {
            id = "convention.kmp.library.compose"
            implementationClass = "KMPLibraryComposeConventionPlugin"
        }
        register("kotlinLibraryComposeAndroid") {
            id = "convention.android.library.compose"
            implementationClass = "KotlinLibraryComposePlugin"
        }
        register("kotlinLibraryRoom") {
            id = "convention.kmp.library.room"
            implementationClass = "RoomLibraryConventionPlugin"
        }
    }
}