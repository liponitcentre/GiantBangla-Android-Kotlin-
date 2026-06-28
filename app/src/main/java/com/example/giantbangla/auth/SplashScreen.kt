package com.example.giantbangla.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.giantbangla.ui.theme.Green
import com.example.giantbangla.ui.theme.ScreenBackground
import com.example.giantbangla.ui.theme.TextSecondary
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier,
    timeoutMillis: Long = 1800L,
) {
    LaunchedEffect(Unit) {
        delay(timeoutMillis)
        onTimeout()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Placeholder logo glyph — swap for your exported asset.
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(Green, RoundedCornerShape(6.dp)),
                )
                Text(
                    text = "Business",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Green,
                    modifier = Modifier.padding(start = 10.dp),
                )
            }
            Text(
                text = "Business logo",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                modifier = Modifier.padding(top = 6.dp),
            )
        }

        // Bottom home indicator.
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
                .width(120.dp)
                .height(4.dp)
                .background(Green, RoundedCornerShape(2.dp)),
        )
    }
}
