package com.escom.calmind.repository.impl;

import android.content.Context;
import android.os.Build;

import com.escom.calmind.R;
import com.escom.calmind.repository.StressQuestionsRepository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class StressQuestionsRepositoryImpl implements StressQuestionsRepository {

    private final Context context;

    @Inject
    public StressQuestionsRepositoryImpl(@ApplicationContext Context context) {
        this.context = context;
    }

    @Override
    public List<String> getAll() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            return Arrays.stream(context.getResources().getStringArray(R.array.questions)).toList();
        } else {
            String[] arr = context.getResources().getStringArray(R.array.questions);
            return new LinkedList<>(Arrays.asList(arr));
        }
    }
}
