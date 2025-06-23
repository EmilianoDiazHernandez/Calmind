package com.escom.calmind.utils

import com.escom.calmind.model.TestResult
import com.escom.calmind.ui.route.CongratulationDialog

fun TestResult.toRoute(): CongratulationDialog =
    CongratulationDialog(stressResult, resilienceResult, traumaResult)