package com.escom.calmind.di;

import com.escom.calmind.repository.StressQuestionsRepository;
import com.escom.calmind.repository.impl.StressQuestionsRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Binds
    public abstract StressQuestionsRepository providesStressRepository(StressQuestionsRepositoryImpl stressTest);
}
