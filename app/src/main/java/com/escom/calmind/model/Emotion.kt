package com.escom.calmind.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Emotion(@SerialName("emociones") val emotions: List<EmotionResponse>)
