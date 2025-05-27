package com.escom.calmind.model;

public class TestResult {

    private StressResult stressResult;
    private AttachmentResult attachmentResult;
    private TraumaResult traumaResult;

    public TestResult(
            StressResult stressResult,
            AttachmentResult attachmentResult,
            TraumaResult traumaResult
    ) {
        this.stressResult = stressResult;
        this.attachmentResult = attachmentResult;
        this.traumaResult = traumaResult;
    }


    public StressResult getStressResult() {
        return stressResult;
    }

    public void setStressResult(StressResult stressResult) {
        this.stressResult = stressResult;
    }

    public AttachmentResult getAttachmentResult() {
        return attachmentResult;
    }

    public void setAttachmentResult(AttachmentResult attachmentResult) {
        this.attachmentResult = attachmentResult;
    }

    public TraumaResult getTraumaResult() {
        return traumaResult;
    }

    public void setTraumaResult(TraumaResult traumaResult) {
        this.traumaResult = traumaResult;
    }
}
