package com.escom.calmind.ui.route

import com.escom.calmind.model.TestResult
import kotlinx.serialization.Serializable

@Serializable
data class CongratulationDialog(val testResult: TestResult)
