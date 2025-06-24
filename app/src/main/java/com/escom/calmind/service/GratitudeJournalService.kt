package com.escom.calmind.service

import com.escom.calmind.model.EmotionResponse

interface GratitudeJournalService {

    suspend fun save(userId: String, text: String): List<EmotionResponse>

}