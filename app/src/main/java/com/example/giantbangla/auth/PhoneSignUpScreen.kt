package com.example.giantbangla.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.giantbangla.components.PhoneField
import com.example.giantbangla.components.PrimaryButton
import com.example.giantbangla.ui.theme.Dimens
import com.example.giantbangla.ui.theme.DividerColor
import com.example.giantbangla.ui.theme.Green
import com.example.giantbangla.ui.theme.ScreenBackground
import com.example.giantbangla.ui.theme.TextSecondary

/**
 * Phone sign-up screen — matches the richest Figma variant (country code +
 * social sign-in). The three Figma frames are visual states of this one screen:
 *
 *  - [ctaText] = "Send Code Via SMS" / "Continue" / "Send OTP"
 *  - [subtitle] = "We'll send a code..." / "Please enter your mobile number"
 *  - [showCountryCode] / [showSocial] toggle the lighter variants.
 */

@Composable
fun PhoneSignUpScreen(
    onSubmit: (String) -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = "Sign up to Giant BanglaApp",
    subtitle: String = "We'll send a code to your mobile number",
    ctaText: String = "Send Code Via SMS",
    showCountryCode: Boolean = true,
    showSocial: Boolean = true,
    onGoogle: () -> Unit = {},
    onFacebook: () -> Unit = {},
    onApple: () -> Unit = {},
) {
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.ScreenPadding),
    ) {
        Spacer(Modifier.height(Dimens.Space48))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = Green,
        )
        Spacer(Modifier.height(Dimens.Space8))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
        )

        Spacer(Modifier.height(Dimens.Space24))
        PhoneField(
            value = phone,
            onValueChange = { phone = it },
            showCountryCode = showCountryCode,
            placeholder = if (showCountryCode) "Enter your mobile number" else "Enter your Number",
        )

        Spacer(Modifier.height(Dimens.Space16))
        PrimaryButton(
            text = ctaText,
            onClick = { onSubmit(phone) },
            enabled = phone.isNotBlank(),
        )

        if (showSocial) {
            Spacer(Modifier.height(Dimens.Space32))
            OrDivider()
            Spacer(Modifier.height(Dimens.Space24))
            SocialRow(onGoogle = onGoogle, onFacebook = onFacebook, onApple = onApple)
        }

        Spacer(Modifier.weight(1f))

        AlreadyHaveAccount(onLoginClick = onLoginClick)
        Spacer(Modifier.height(Dimens.Space32))
    }
}

@Composable
private fun OrDivider() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Divider(color = DividerColor, modifier = Modifier
            .weight(1f)
            .height(1.dp))
        Text(
            text = "Or sign in with",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
            modifier = Modifier.padding(horizontal = 12.dp),
        )
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            thickness = DividerDefaults.Thickness,
            color = DividerColor
        )
    }
}

@Composable
private fun SocialRow(onGoogle: () -> Unit, onFacebook: () -> Unit, onApple: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.Space16, Alignment.CenterHorizontally),
        modifier = Modifier.fillMaxWidth(),
    ) {
        // Replace placeholders with:
        //   SocialButton(painterResource(R.drawable.ic_google), "Google", onGoogle)
        //   SocialButton(painterResource(R.drawable.ic_facebook), "Facebook", onFacebook)
        //   SocialButton(painterResource(R.drawable.ic_apple), "Apple", onApple)
        SocialPlaceholder("G", onGoogle)
        SocialPlaceholder("f", onFacebook)
        SocialPlaceholder("", onApple)
    }
}

@Composable
private fun SocialPlaceholder(label: String, onClick: () -> Unit) {
    androidx.compose.material3.OutlinedButton(
        onClick = onClick,
        modifier = Modifier.height(48.dp),
    ) { Text(label) }
}

@Composable
private fun AlreadyHaveAccount(onLoginClick: () -> Unit) {
    val text = buildAnnotatedString {
        append("Already Have Account? ")
        withStyle(SpanStyle(color = Green, fontWeight = FontWeight.SemiBold)) {
            append("Login Now")
        }
    }
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = TextSecondary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            //.clickableNoRipple(onLoginClick),
    )
}

// Small helper to keep the login link tappable without a ripple box.

//@SuppressLint("RememberInComposition")
//@Composable
//private fun Modifier.clickableNoRipple(onClick: () -> Unit): Modifier =
//    this.then(
//        androidx.compose.foundation.clickable(
//            interactionSource = androidx.compose.foundation.interaction.MutableInteractionSource(),
//            indication = null,
//            onClick = onClick,
//        ),
//    )


@Preview
@Composable
fun ShowPhoneSignUpScreen(modifier: Modifier = Modifier) {
    PhoneSignUpScreen(
        onSubmit = { },
        onLoginClick = { },
    )}