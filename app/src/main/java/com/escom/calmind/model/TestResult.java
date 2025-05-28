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

    public ResilienceResult getAttachmentResult() {
        return resilienceResult;
    }

    public void setAttachmentResult(ResilienceResult resilienceResult) {
        this.resilienceResult = resilienceResult;
    }

    public TraumaResult getTraumaResult() {
        return traumaResult;
    }

    public void setTraumaResult(TraumaResult traumaResult) {
        this.traumaResult = traumaResult;
    }
}
