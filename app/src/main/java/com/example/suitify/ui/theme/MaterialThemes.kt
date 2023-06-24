package com.example.suitify.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.example.suitify.R
import com.example.suitify.ui.extention.advancedShadow

@Composable
fun TextStyleTheme(modifier: Modifier, text: String, size: TextUnit, color: Color) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = size,
        color = color,
        maxLines = 1,
        textAlign = TextAlign.Center,
        style = TextStyle(fontStyle = FontStyle(R.font.urbanist_black))
    )
}

@Composable
fun ImageStyleTheme(modifier: Modifier, painter: Painter) {
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun MusicButtons(modifier: Modifier, boxSize: Dp, imageSize: Dp, imageIcon: Int) {
    Box(
        modifier = modifier
            .size(boxSize)
            .advancedShadow(color = ButtonShadowColor, shadowBlurRadius = dp32)
            .shadow(elevation = dp16, shape = RoundedCornerShape(dp32))
            .background(ButtonColor),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = imageIcon),
            contentDescription = null,
            modifier = Modifier.size(imageSize),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun MusicProgressText(text: String) {
    Text(
        text = text,
        fontSize = sp14,
        style = TextStyle(fontStyle = FontStyle(R.font.urbanist_black)),
        maxLines = 1,
        color = MainTextColor
    )
}