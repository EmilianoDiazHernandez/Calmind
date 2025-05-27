package com.escom.calmind.ui.shape

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.geometry.Rect

fun semiCircleShape(radius: Float) = GenericShape { size, _ ->
    size.width
    val shapeRadius = radius/* ?: (size.width / 2.0f) */
    moveTo(0f, size.height)
    lineTo(0f, shapeRadius)
    arcTo(
        rect = Rect(
            left = 0f,
            top = 0f,
            right = size.width,
            bottom = size.width
        ),
        startAngleDegrees = 180f,
        sweepAngleDegrees = 180f,
        forceMoveTo = false
    )
    lineTo(size.width, size.height)
    close()
}