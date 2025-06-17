package com.escom.calmind.model;

public class TestResult {

    private final StressResult stressResult;
    private final ResilienceResult resilienceResult;
    private final TraumaResult traumaResult;

    public TestResult(StressResult stressResult, ResilienceResult resilienceResult, TraumaResult traumaResult) {
        this.stressResult = stressResult;
        this.resilienceResult = resilienceResult;
        this.traumaResult = traumaResult;
    }

    public TraumaResult getTraumaResult() {
        return traumaResult;
    }

    public ResilienceResult getResilienceResult() {
        return resilienceResult;
    }

    public StressResult getStressResult() {
        return stressResult;
    }
}
