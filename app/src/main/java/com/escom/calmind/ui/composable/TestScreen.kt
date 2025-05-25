package com.escom.calmind.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.escom.calmind.databinding.TestViewBinding

@Composable
fun TestScreen() {
    AndroidViewBinding(TestViewBinding::inflate) {
    }
}