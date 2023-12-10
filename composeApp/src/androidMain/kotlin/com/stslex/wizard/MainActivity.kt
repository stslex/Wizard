package com.stslex.wizard

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.components.resources.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App {
                if (BuildConfig.DEBUG) {
                    androidLogger()
                }
                androidContext(this@MainActivity)
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
