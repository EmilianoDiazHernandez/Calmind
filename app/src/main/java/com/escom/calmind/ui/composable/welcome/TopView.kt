package com.escom.calmind.ui.composable.welcome

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import com.escom.calmind.R
import com.escom.calmind.ui.shape.CustomRotatingMorphShape

@Composable
fun TopView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Start)
                .size(100.dp)
                .padding(top = 8.dp, start = 8.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            val shapeA = remember {
                RoundedPolygon(
                    10,
                    rounding = CornerRounding(0.2f)
                )
            }
            val shapeB = remember {
                RoundedPolygon.star(
                    12,
                    rounding = CornerRounding(0.2f)
                )
            }
            val morph = remember {
                Morph(shapeA, shapeB)
            }
            val infiniteTransition =
                rememberInfiniteTransition("infinite outline movement")
            val animatedProgress = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    tween(2000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "animatedMorphProgress"
            )
            val animatedRotation = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    tween(6000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "animatedMorphProgress"
            )
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(
                        CustomRotatingMorphShape(
                            morph,
                            animatedProgress.value,
                            animatedRotation.value
                        )
                    )
                    .background(Color.Black.copy(alpha = 0.3f))
                    .align(Alignment.BottomCenter)
            )
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}