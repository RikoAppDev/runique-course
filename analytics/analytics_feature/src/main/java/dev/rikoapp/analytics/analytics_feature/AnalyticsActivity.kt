package dev.rikoapp.analytics.analytics_feature

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.play.core.splitcompat.SplitCompat
import dev.rikoapp.analytics.data.di.analyticsModule
import dev.rikoapp.analytics.presentation.AnalyticsDashboardScreenRoot
import dev.rikoapp.analytics.presentation.di.analyticsPresentationModule
import dev.rikoapp.core.presentation.designsystem.RuniqueCourseTheme
import dev.rikoapp.runiquecourse.R
import org.koin.core.context.loadKoinModules

class AnalyticsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(
            listOf(
                analyticsModule,
                analyticsPresentationModule
            )
        )
        SplitCompat.installActivity(this)

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    closeWithAnimation()
                }
            }
        )

        setContent {
            RuniqueCourseTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "analytics_dashboard"
                ) {
                    composable("analytics_dashboard") {
                        AnalyticsDashboardScreenRoot(
                            onBackClick = { closeWithAnimation() }
                        )
                    }
                }
            }
        }
    }

    private fun closeWithAnimation() {
        finish()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                OVERRIDE_TRANSITION_CLOSE,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
        } else {
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }
}