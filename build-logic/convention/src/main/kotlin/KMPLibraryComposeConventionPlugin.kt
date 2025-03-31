import AppExt.findPluginId
import AppExt.libs
import com.android.build.api.dsl.LibraryExtension
import com.stslex.wizard.convention.configureKMPCompose
import com.stslex.wizard.convention.configureKotlin
import com.stslex.wizard.convention.configureKotlinAndroid
import com.stslex.wizard.convention.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KMPLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.findPluginId("kotlinMultiplatform"))
            apply(libs.findPluginId("jetbrainsCompose"))
            apply(libs.findPluginId("composeCompiler"))
            apply(libs.findPluginId("kotlinCocoapods"))
            apply(libs.findPluginId("androidLibrary"))
            apply(libs.findPluginId("serialization"))
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureKMPCompose(
                extension = this,
                compose = extensions.getByType<ComposePlugin.Dependencies>()
            )
            configureKotlinMultiplatform(extension = this)
        }

        extensions.configure<LibraryExtension> {
            configureKotlin()
            configureKotlinAndroid(this)
        }
    }
}