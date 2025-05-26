package com.escom.calmind.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.escom.calmind.repository.StressQuestionsRepository;

import java.util.Arrays;
import java.util.List;

public class StressQuestionsViewModel extends ViewModel {

    private final List<String> questions;
    private int currentIndex = 0;

    private final MutableLiveData<String> currentQuestion = new MutableLiveData<>();
    private final MutableLiveData<Boolean> finished = new MutableLiveData<>(false);

    public StressQuestionsViewModel(StressQuestionsRepository repository) {
        this.questions = Arrays.asList(repository.getAll());
        if (!questions.isEmpty()) {
            currentQuestion.setValue(questions.get(0));
        } else {
            finished.setValue(true);
        }
    }

    public LiveData<String> getCurrentQuestion() {
        return currentQuestion;
    }

    public LiveData<Boolean> isFinished() {
        return finished;
    }

    public void onQuestionAnswered() {
        currentIndex++;
        if (currentIndex < questions.size()) {
            currentQuestion.setValue(questions.get(currentIndex));
        } else {
            finished.setValue(true);
        }
    }
}
