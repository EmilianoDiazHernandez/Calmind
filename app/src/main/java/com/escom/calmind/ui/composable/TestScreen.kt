package com.escom.calmind.ui.composable

import android.widget.RadioButton
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.escom.calmind.R
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
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {
                    Button(onClick = onConfirmDialog) {
                        Text(stringResource(R.string.go))
                    }
                },
                title = {
                    Text(
                        stringResource(R.string.congratulations),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            currentUser?.name.orEmpty(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            stringResource(R.string.message_successfully),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        val testResults = onTestResultsAvailable()
                        //HorizontalPager() { }
                        val stressMessage: String = stringResource(
                            when (testResults.stressResult!!) {
                                StressResult.LOW -> R.string.stress_low
                                StressResult.MIDDLE -> R.string.stress_medium
                                StressResult.HIGH -> R.string.stress_high
                            }
                        )
                        Text(
                            stressMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                        //val result = results

                    }
                }
            )
        }
        AndroidViewBinding(TestViewBinding::inflate, modifier = Modifier.fillMaxSize()) {
            question.text = currentQuestion
            btnConfirm.setOnClickListener {
                if (radioGroup.checkedRadioButtonId != -1)
                    onQuestionAnswered(
                        radioGroup.indexOfChild(
                            radioGroup.findViewById<RadioButton>(
                                radioGroup.checkedRadioButtonId
                            )
                        )
                    )
                else
                    Toast.makeText(
                        root.context,
                        root.context.getString(R.string.invalid_answer),
                        Toast.LENGTH_SHORT
                    ).show()
            }
            radioGroup.clearCheck()
        }
    }
}

@Preview(device = "id:pixel_5", showSystemUi = true, showBackground = true)
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
                        TraumaResult.NO_TEPT
                    )
                },
                isTestFinished = true
            ) { }
        }
    }

}
