package com.escom.calmind.di

import com.escom.calmind.repository.TestResultRepository
import com.escom.calmind.repository.UserDataRepository
import com.escom.calmind.repository.impl.TestResultRepositoryImpl
import com.escom.calmind.repository.impl.UserDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDataModule {

    @Binds
    abstract fun bindsDataRepository(dataRepository: UserDataRepositoryImpl): UserDataRepository

    @Binds
    abstract fun bindsTestResults(testResultRepository: TestResultRepositoryImpl): TestResultRepository

}