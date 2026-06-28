package com.example.giantbangla.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.giantbangla.components.OtpInputField
import com.example.giantbangla.components.PrimaryButton
import com.example.giantbangla.ui.theme.Dimens
import com.example.giantbangla.ui.theme.Green
import com.example.giantbangla.ui.theme.ScreenBackground
import com.example.giantbangla.ui.theme.TextSecondary
import kotlinx.coroutines.delay

/**
 * OTP verification screen. The "empty" and "filled" Figma frames are just the
 * empty vs. populated states of [otp]; the resend timer counts down from 50s.
 */
@Composable
fun OtpVerificationScreen(
    phoneNumber: String,
    onVerify: (String) -> Unit,
    onResend: () -> Unit,
    modifier: Modifier = Modifier,
    otpLength: Int = 6,
    resendSeconds: Int = 50,
) {
    var otp by remember { mutableStateOf("") }
    var secondsLeft by remember { mutableIntStateOf(resendSeconds) }

    LaunchedEffect(resendSeconds) {
        secondsLeft = resendSeconds
        while (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(horizontal = Dimens.ScreenPadding),
    ) {
        Spacer(Modifier.height(Dimens.Space48))

        Text(
            text = "Number Verification",
            style = MaterialTheme.typography.headlineSmall,
            color = Green,
        )
        Spacer(Modifier.height(Dimens.Space8))
        Text(
            text = "Please enter the 6 digit code send to : $phoneNumber",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
        )

        Spacer(Modifier.height(Dimens.Space24))
        OtpInputField(
            otpText = otp,
            onOtpChange = { otp = it },
            otpCount = otpLength,
        )

        Spacer(Modifier.height(Dimens.Space16))
        ResendRow(
            secondsLeft = secondsLeft,
            onResend = {
                if (secondsLeft == 0) {
                    secondsLeft = resendSeconds
                    onResend()
                }
            },
        )

        Spacer(Modifier.height(Dimens.Space32))
        PrimaryButton(
            text = "Continue",
            onClick = { onVerify(otp) },
            enabled = otp.length == otpLength,
        )

        Spacer(Modifier.weight(1f))
    }
}

@Composable
private fun ResendRow(secondsLeft: Int, onResend: () -> Unit) {
    val expired = secondsLeft == 0
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = if (expired) "Resend code" else "Resend the code",
            style = MaterialTheme.typography.bodyMedium,
            color = if (expired) Green else TextSecondary,
            fontWeight = if (expired) FontWeight.SemiBold else FontWeight.Normal,
            modifier = Modifier.then(
                if (expired) Modifier.padding(end = 4.dp) else Modifier.padding(end = 6.dp),
            ),
        )
        Text(
            text = "%02ds".format(secondsLeft),
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
        )
    }
}
