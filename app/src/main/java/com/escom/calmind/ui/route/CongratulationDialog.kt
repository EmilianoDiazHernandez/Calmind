package com.escom.calmind.ui.route

import com.escom.calmind.model.ResilienceResult
import com.escom.calmind.model.StressResult
import com.escom.calmind.model.TraumaResult
import kotlinx.serialization.Serializable

@Serializable
data class CongratulationDialog(
    val stressResult: StressResult,
    val resilienceResult: ResilienceResult,
    val traumaResult: TraumaResult
)
