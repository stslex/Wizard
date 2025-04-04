import AppExt.APP_PREFIX
import AppExt.findPluginId
import AppExt.findVersionInt
import AppExt.findVersionString
import AppExt.libs
import com.android.build.api.dsl.ApplicationExtension
import com.stslex.wizard.convention.configureKMPCompose
import com.stslex.wizard.convention.configureKMPComposeNavigation
import com.stslex.wizard.convention.configureKotlin
import com.stslex.wizard.convention.configureKotlinAndroid
import com.stslex.wizard.convention.configureKotlinAndroidCompose
import com.stslex.wizard.convention.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KMPApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.findPluginId("kotlinMultiplatform"))
            apply(libs.findPluginId("kotlinCocoapods"))
            apply(libs.findPluginId("androidApplication"))
            apply(libs.findPluginId("jetbrainsCompose"))
            apply(libs.findPluginId("composeCompiler"))
            apply(libs.findPluginId("serialization"))
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureKotlinMultiplatform(this)
            configureKMPCompose(
                extension = this,
                compose = extensions.getByType<ComposePlugin.Dependencies>()
            )
            configureKMPComposeNavigation(this)
        }

        extensions.configure<ApplicationExtension> {
            configureKotlin()
            configureKotlinAndroid(
                extension = this,
                isApp = true
            )
            configureKotlinAndroidCompose(this)
            defaultConfig.apply {
                applicationId = APP_PREFIX
                targetSdk = libs.findVersionInt("targetSdk")
                versionName = libs.findVersionString("versionName")
                versionCode = libs.findVersionInt("versionCode")
            }
        }
    }
}