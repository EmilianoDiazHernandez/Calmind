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
        return new String[]{
                context.getString(R.string.stress_question01),
                context.getString(R.string.stress_question02),
                context.getString(R.string.stress_question03),
                context.getString(R.string.stress_question04),
                context.getString(R.string.stress_question05),
                context.getString(R.string.stress_question06),
                context.getString(R.string.stress_question07),
                context.getString(R.string.stress_question08),
                context.getString(R.string.stress_question09),
                context.getString(R.string.stress_question10),
                context.getString(R.string.stress_question11),
                context.getString(R.string.stress_question12),
                context.getString(R.string.stress_question13),
                context.getString(R.string.stress_question14)};
    }
}
