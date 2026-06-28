package com.example.giantbangla.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import com.example.giantbangla.ui.theme.Dimens
import com.example.giantbangla.ui.theme.FieldBackground
import com.example.giantbangla.ui.theme.Green
import com.example.giantbangla.ui.theme.OtpBorder
import com.example.giantbangla.ui.theme.TextPrimary

/**
 * 6-cell OTP entry. A single hidden BasicTextField captures input from the
 * system numeric keyboard; each digit is rendered into its own box.
 *
 * Empty state  -> grey-bordered empty boxes.
 * Filled state -> green-bordered boxes containing the typed digits.
 */
@Composable
fun OtpInputField(
    otpText: String,
    onOtpChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    otpCount: Int = 6,
) {
    BasicTextField(
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = { newValue ->
            val digits = newValue.text.filter { it.isDigit() }.take(otpCount)
            onOtpChange(digits)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        modifier = modifier,
        decorationBox = {
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.Space8)) {
                repeat(otpCount) { index ->
                    OtpCell(
                        char = otpText.getOrNull(index),
                        isActive = index == otpText.length,
                    )
                }
            }
        },
    )
}

@Composable
private fun OtpCell(char: Char?, isActive: Boolean) {
    val filled = char != null
    val borderColor = if (filled || isActive) Green else OtpBorder
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(FieldBackground, RoundedCornerShape(Dimens.OtpBoxRadius))
            .border(1.5.dp, borderColor, RoundedCornerShape(Dimens.OtpBoxRadius)),
    ) {
        Text(
            text = char?.toString() ?: "",
            style = MaterialTheme.typography.titleLarge,
            color = TextPrimary,
        )
    }
}
