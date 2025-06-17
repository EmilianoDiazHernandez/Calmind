package com.escom.calmind.model;

public class TestResult {

    private StressResult stressResult;
    private ResilienceResult resilienceResult;
    private TraumaResult traumaResult;

    public TestResult(
            StressResult stressResult,
            ResilienceResult resilienceResult,
            TraumaResult traumaResult
    ) {
        this.stressResult = stressResult;
        this.resilienceResult = resilienceResult;
        this.traumaResult = traumaResult;
    }


    public StressResult getStressResult() {
        return stressResult;
    }

    public void setStressResult(StressResult stressResult) {
        this.stressResult = stressResult;
    }

    public ResilienceResult getResilienceResult() {
        return resilienceResult;
    }

    public void setResilienceResult(ResilienceResult resilienceResult) {
        this.resilienceResult = resilienceResult;
    }

    public TraumaResult getTraumaResult() {
        return traumaResult;
    }

    public void setTraumaResult(TraumaResult traumaResult) {
        this.traumaResult = traumaResult;
    }
}
