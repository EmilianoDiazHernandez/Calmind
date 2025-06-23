package com.escom.calmind.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingFullScreen(modifier: Modifier = Modifier) = Surface {
    Box(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier.align(Alignment.Center))
    }
}