package com.escom.calmind.service.impl

import com.escom.calmind.model.Emotion
import com.escom.calmind.model.EmotionResponse
import com.escom.calmind.service.GratitudeJournalService
import com.escom.calmind.utils.BASE_URL_EMOTIONS
import com.escom.calmind.utils.USER_COLLECTION
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GratitudeJournalServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val httpClient: HttpClient
) : GratitudeJournalService {

    override suspend fun save(userId: String, text: String): List<EmotionResponse> {
        val response = httpClient.use {
            it.post(BASE_URL_EMOTIONS) {
                contentType(ContentType.Application.Json)
                setBody("{ \"texto\" : \"${text}\" }")
            }.body<Emotion>()
        }
        db.collection(USER_COLLECTION).document(userId)
            .update("gratitudeDays", FieldValue.increment(1)).await()
        return response.emotions
    }

}
