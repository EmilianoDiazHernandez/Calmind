package com.escom.calmind.di

import com.escom.calmind.service.AuthService
import com.escom.calmind.service.impl.FirebaseAuthService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindsAuthService(service: FirebaseAuthService): AuthService

}