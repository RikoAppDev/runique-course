package dev.rikoapp.wear.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.rikoapp.core.presentation.designsystem_wear.RuniqueCourseTheme
import dev.rikoapp.wear.run.presentation.TrackerScreenRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            RuniqueCourseTheme {
                TrackerScreenRoot()
            }
        }
    }
}