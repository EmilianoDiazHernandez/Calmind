package com.escom.calmind.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.escom.calmind.model.ResilienceResult;
import com.escom.calmind.model.StressResult;
import com.escom.calmind.model.TestResult;
import com.escom.calmind.model.TraumaResult;
import com.escom.calmind.model.UserData;
import com.escom.calmind.repository.StressQuestionsRepository;
import com.escom.calmind.repository.UserDataRepository;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class StressQuestionsViewModel extends ViewModel {

    private final List<String> questions;
    private int currentIndex = 0;

    private final MutableLiveData<String> currentQuestion = new MutableLiveData<>();
    private final MutableLiveData<Boolean> finished = new MutableLiveData<>(false);
    private Integer stress = 0; // 0-10 low | 11-25 medium | 26-30 high
    private Integer resilience = 0; // 0-20 low | 21-30 medium | 31-40 high
    private Integer trauma = 0; // 0–30 No TEPT | 31–33 Possible TEPT | 34: highly possibility TEPT
    public LiveData<UserData> userDataLiveData;

    @Inject
    public StressQuestionsViewModel(
            StressQuestionsRepository stressRepository,
            UserDataRepository userDataRepository
    ) {
        this.userDataLiveData = userDataRepository.getData();
        this.questions = Arrays.asList(stressRepository.getAll());
        if (!questions.isEmpty())
            currentQuestion.setValue(questions.get(0));
        else
            finished.setValue(true);
    }

    private TraumaResult getTraumaResult() {
        if (trauma <= 30) return TraumaResult.NO_PTSD;
        if (trauma <= 33) return TraumaResult.PROBABLE_PTSD;
        return TraumaResult.HIGHLY_PROBABLE_PTSD;
    }

    private ResilienceResult getResilience() {
        if (resilience <= 20) return ResilienceResult.LOW;
        if (resilience <= 30) return ResilienceResult.MIDDLE;
        return ResilienceResult.HIGH;
    }

    private StressResult getStress() {
        if (stress <= 10) return StressResult.LOW;
        if (stress <= 25) return StressResult.MIDDLE;
        return StressResult.HIGH;
    }

    public TestResult getTestResult() {
        return new TestResult(getStress(), getResilience(), getTraumaResult());
    }

    public LiveData<String> getCurrentQuestion() {
        return currentQuestion;
    }

    public LiveData<Boolean> isFinished() {
        return finished;
    }

    public void onQuestionAnswered(int answer) {

        if (currentIndex < 14) {
            stress += answer;
        } else if (currentIndex <= 24) {
            resilience += answer;
        } else {
            trauma += answer;
        }
        currentIndex++;

        if (currentIndex < questions.size())
            currentQuestion.setValue(questions.get(currentIndex));
        else
            finished.setValue(true);
    }

}
