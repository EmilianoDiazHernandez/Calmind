package com.escom.calmind.ui.composable.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.escom.calmind.model.EmotionResponse

@SuppressLint("DefaultLocale")
@Composable
fun EmotionItem(modifier: Modifier = Modifier, emotionResponse: EmotionResponse) {
    Column(modifier = modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
        with(emotionResponse) {
            Text(emotion.replaceFirstChar { it.uppercaseChar() })
            Text(String.format("%.2f", trust))
            Text(getEmoji(emotion), fontSize = 48.sp)
        }
    }
}

private fun getEmoji(emotion: String) = when (emotion.lowercase()) {
    "admiration" -> "🌟"
    "amusement" -> "😄"
    "anger" -> "😠"
    "annoyance" -> "😒"
    "approval" -> "👍"
    "caring" -> "🤗"
    "confusion" -> "😕"
    "curiosity" -> "🤔"
    "desire" -> "😍"
    "disappointment" -> "😞"
    "disapproval" -> "👎"
    "disgust" -> "🤢"
    "embarrassment" -> "😳"
    "excitement" -> "🤩"
    "fear" -> "😨"
    "gratitude" -> "🙏"
    "grief" -> "😢"
    "joy" -> "😃"
    "love" -> "❤"
    "nervousness" -> "😬"
    "optimism" -> "🌈"
    "pride" -> "🏅"
    "realization" -> "💡"
    "relief" -> "😌"
    "remorse" -> "😔"
    "sadness" -> "😔"
    "surprise" -> "😲"
    "neutral" -> "😐"
    else -> "😐"
}