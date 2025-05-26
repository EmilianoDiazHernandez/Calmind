package com.escom.calmind.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.escom.calmind.repository.StressQuestionsRepository;

public class StressQuestionsViewModelFactory implements ViewModelProvider.Factory {

    private final StressQuestionsRepository repository;

    public StressQuestionsViewModelFactory(StressQuestionsRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StressQuestionsViewModel.class)) {
            return (T) new StressQuestionsViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
