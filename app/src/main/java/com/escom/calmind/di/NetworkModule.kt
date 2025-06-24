package com.escom.calmind.di

import com.escom.calmind.service.GratitudeJournalService
import com.escom.calmind.service.impl.GratitudeJournalServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpClient() = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json()
        }
    }

    @Provides
    fun providesGratitudeJournalService(service: GratitudeJournalServiceImpl): GratitudeJournalService =
        service

}