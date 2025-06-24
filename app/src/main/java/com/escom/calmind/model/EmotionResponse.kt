package com.escom.calmind.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmotionResponse(
    @SerialName("emocion")
    val emotion: String,
    @SerialName("confianza")
    val trust: Double
)
