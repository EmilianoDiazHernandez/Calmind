package com.escom.calmind.repository.impl;

import android.content.Context;

import com.escom.calmind.R;
import com.escom.calmind.repository.StressQuestionsRepository;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class StressQuestionsRepositoryImpl implements StressQuestionsRepository {

    private final Context context;

    @Inject
    public StressQuestionsRepositoryImpl(@ApplicationContext Context context) {
        this.context = context;
    }

    @Override
    public String[] getAll() {
        return context.getResources().getStringArray(R.array.questions);
    }
}
