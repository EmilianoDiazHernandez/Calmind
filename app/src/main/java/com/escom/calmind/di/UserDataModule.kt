package com.escom.calmind.di

import com.escom.calmind.repository.UserDataRepository
import com.escom.calmind.repository.impl.UserDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDataModule {

    @Provides
    @Singleton
    fun provideDataRepository(dataRepository: UserDataRepositoryImpl): UserDataRepository = dataRepository

}