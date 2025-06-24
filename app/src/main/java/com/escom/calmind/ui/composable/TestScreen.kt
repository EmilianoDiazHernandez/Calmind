package com.escom.calmind.ui.composable

import android.widget.RadioButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.escom.calmind.databinding.TestViewBinding
import com.escom.calmind.model.ResilienceResult
import com.escom.calmind.model.StressResult
import com.escom.calmind.model.TestResult
import com.escom.calmind.model.TraumaResult
import com.escom.calmind.model.UserData
import com.escom.calmind.ui.theme.CalmindTheme


@Composable
fun TestScreen(
    modifier: Modifier = Modifier,
    currentQuestion: String,
    onQuestionAnswered: (answer: Int) -> Unit,
    onTestResultsAvailable: () -> TestResult,
    currentUser: UserData?,
    isTestFinished: Boolean,
    onConfirmDialog: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        if (isTestFinished) {
            val pageState = rememberPagerState(pageCount = { 4 })
            CongratulationDialog(
                onTestResultsAvailable = onTestResultsAvailable,
                currentUser = currentUser, onConfirmDialog = onConfirmDialog,
                pageState = pageState
            )
        }
        AndroidViewBinding(TestViewBinding::inflate, modifier = Modifier.fillMaxSize()) {
            question.text = currentQuestion
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                btnConfirm.isEnabled = checkedId != -1
            }
            btnConfirm.setOnClickListener {
                onQuestionAnswered(
                    radioGroup.indexOfChild(
                        radioGroup.findViewById<RadioButton>(
                            radioGroup.checkedRadioButtonId
                        )
                    )
                )
            }
            radioGroup.clearCheck()
        }
    }
}

@Preview(device = "id:Nexus 5", showSystemUi = true, showBackground = true)
@Composable
private fun TestScreenPreview() {
    CalmindTheme {
        Surface {
            TestScreen(
                modifier = Modifier,
                currentQuestion = "How do you feel today?",
                onQuestionAnswered = {},
                currentUser = UserData("Diego", 13, emptyList(), "High School"),
                onTestResultsAvailable = {
                    TestResult(
                        StressResult.LOW,
                        ResilienceResult.LOW,
                        TraumaResult.LOW
                    )
                },
                isTestFinished = false
            ) { }
        }
    }
}
