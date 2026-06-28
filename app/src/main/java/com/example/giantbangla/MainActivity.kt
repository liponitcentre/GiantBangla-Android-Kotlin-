package com.example.giantbangla
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.giantbangla.auth.AuthNavGraph
import com.example.giantbangla.onboarding.OnboardingOne
import com.example.giantbangla.ui.theme.GiantBanglaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            GiantBanglaTheme {
                Box (modifier = Modifier){
                    AuthNavGraph()
                }
            }
        }
    }
}