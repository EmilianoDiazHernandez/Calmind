package com.escom.calmind.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TestResult(
    val stressResult: StressResult,
    val resilienceResult: ResilienceResult,
    val traumaResult: TraumaResult
) : Parcelable
