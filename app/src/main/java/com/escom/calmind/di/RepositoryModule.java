package com.escom.calmind.di;

import com.escom.calmind.repository.StressQuestionsRepository;
import com.escom.calmind.repository.impl.StressQuestionsRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Provides
    public static StressQuestionsRepository providesStressRepository(StressQuestionsRepositoryImpl stressTest) {
        return stressTest;
    }
}
