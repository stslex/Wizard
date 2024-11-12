import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.github.gmazzo.gradle.plugins.BuildConfigExtension
import java.util.Properties

plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.buildConfig)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:core"))
            implementation(project(":core:database"))
            implementation(libs.bundles.ktor)
            implementation(libs.slf4j.simple)
            implementation(libs.kotlinx.datetime)
        }
        buildConfig {
            setLocalProperty(project.rootProject)
        }
    }
}

fun BuildConfigExtension.setLocalProperty(dir: Project) {
    val localProperties = gradleLocalProperties(dir.projectDir, providers)

    buildStringField(localProperties, "KINOPOISK_API_KEY")
    buildStringField(localProperties, "SERVER_HOST")
    buildStringField(localProperties, "SERVER_API_VERSION")
    buildStringField(localProperties, "SERVER_PORT")
    buildStringField(localProperties, "SERVER_API_KEY")
}

fun BuildConfigExtension.buildStringField(localProperties: Properties, name: String) {
    buildConfigField("String", name, localProperties.getString(name))
}

fun Properties.getString(key: String): String {
    return getProperty(key) ?: throw IllegalStateException("$key should be initialised")
}
