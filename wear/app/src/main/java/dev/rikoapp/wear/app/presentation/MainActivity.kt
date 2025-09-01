package dev.rikoapp.wear.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.Text
import dev.rikoapp.wear.app.presentation.theme.RuniqueCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            RuniqueCourseTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { /* Do something when the button is clicked */ }
                    ) {
                        Text("Hello, Wear OS!")
                    }
                }
            }
        }
    }
}