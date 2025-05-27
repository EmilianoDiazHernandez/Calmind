package com.escom.calmind.di

import com.escom.calmind.repository.UserDataRepository
import com.escom.calmind.repository.impl.UserDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserDataModule {

    @Provides
    fun provideDataRepository(dataRepository: UserDataRepositoryImpl): UserDataRepository = dataRepository

}