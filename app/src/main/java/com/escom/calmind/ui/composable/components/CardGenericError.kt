package com.escom.calmind.ui.composable.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CardGenericError(
    modifier: Modifier = Modifier,
    visible: Boolean,
    message: @Composable () -> Unit
) {
    AnimatedVisibility(visible) {
        OutlinedCard(modifier = modifier) {
            /**/
            message()
        }
    }
}
