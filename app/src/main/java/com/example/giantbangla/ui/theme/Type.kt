package com.example.giantbangla.ui.theme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Swap [AppFontFamily] for your real font once exported from Figma.
 * e.g. place fonts in res/font/ and use:
 *   val AppFontFamily = FontFamily(
 *       Font(R.font.notosansbengali_regular, FontWeight.Normal),
 *       Font(R.font.notosansbengali_semibold, FontWeight.SemiBold),
 *       Font(R.font.notosansbengali_bold,     FontWeight.Bold),
 *   )
 */
val AppFontFamily = FontFamily.Default

val AppTypography = Typography(
    headlineSmall = TextStyle(           // screen titles ("Sign up to Giant BanglaApp")
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
    ),
    bodyMedium = TextStyle(              // subtitles / helper text
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodyLarge = TextStyle(               // input text
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    ),
    labelLarge = TextStyle(             // button labels
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    titleLarge = TextStyle(            // OTP digits / logo wordmark
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
)