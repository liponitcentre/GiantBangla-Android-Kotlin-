package com.example.giantbangla.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.giantbangla.ui.theme.Dimens
import com.example.giantbangla.ui.theme.FieldBorder

/**
 * Circular social sign-in button (Google / Facebook / Apple).
 * Provide each brand glyph as a drawable, e.g. painterResource(R.drawable.ic_google).
 */
@Composable
fun SocialButton(
    icon: Painter,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(Dimens.SocialButton)
            .border(1.dp, FieldBorder, CircleShape),
    ) {
        Image(
            painter = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(20.dp).padding(0.dp),
        )
    }
}
