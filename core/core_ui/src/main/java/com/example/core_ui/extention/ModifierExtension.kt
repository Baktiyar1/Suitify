package com.example.core_ui.extention

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.dp32

//fun Modifier.clickableWithoutReply(
//    interactionSource: MutableInteractionSource, onClick: () -> Unit
//) = composed(factory = {
//    this.then(
//        Modifier.clickable(interactionSource = interactionSource,
//            indication = null,
//            onClick = { onClick() })
//    )
//})

fun Modifier.advancedShadow(
    color: Color = Color.Black,
    alpha: Float = 1f,
    cornersRadius: Dp = 0.dp,
    shadowBlurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = drawBehind {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()

    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(), offsetX.toPx(), offsetY.toPx(), shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.clickableNoRipple(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.clickableCircleRipple(onClick: () -> Unit): Modifier = composed {
    clip(shape = RoundedCornerShape(dp32))
    clickable { onClick() }
}