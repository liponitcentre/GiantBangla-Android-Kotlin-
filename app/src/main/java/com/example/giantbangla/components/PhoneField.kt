package com.example.giantbangla.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.giantbangla.ui.theme.Dimens
import com.example.giantbangla.ui.theme.FieldBackground
import com.example.giantbangla.ui.theme.FieldBorder
import com.example.giantbangla.ui.theme.TextHint

/**
 * Phone input with a leading country-code chip (🇧🇩 +880).
 * Set [showCountryCode] = false for the plain "Enter your Number" variant.
 */
@Composable
fun PhoneField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Enter your mobile number",
    showCountryCode: Boolean = true,
    countryFlag: String = "\uD83C\uDDE7\uD83C\uDDE9", // 🇧🇩
    countryCode: String = "+880",
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.FieldHeight)
            .border(1.dp, FieldBorder, RoundedCornerShape(Dimens.FieldRadius))
            .background(FieldBackground, RoundedCornerShape(Dimens.FieldRadius)),
    ) {
        if (showCountryCode) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp),
            ) {
                Text(text = countryFlag, style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = countryCode,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 6.dp),
                )
            }
            Divider(
                color = FieldBorder,
                modifier = Modifier.width(1.dp).height(24.dp),
            )
        }

        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextHint,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
