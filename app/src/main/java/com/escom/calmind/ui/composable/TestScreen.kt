package com.escom.calmind.ui.composable

import android.widget.RadioButton
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.hilt.navigation.compose.hiltViewModel
import com.escom.calmind.R
import com.escom.calmind.databinding.TestViewBinding
import com.escom.calmind.ui.viewmodel.StressQuestionsViewModel


@Composable
fun TestScreen() {
    val viewModel = hiltViewModel<StressQuestionsViewModel>()
    val currentQuestion by viewModel.currentQuestion.observeAsState("")
    val currentUser by viewModel.userDataLiveData.observeAsState()
    Column {
        val isFinished by viewModel.isFinished.observeAsState(initial = false)
        if (isFinished) {
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {
                    Button(onClick = {}) {
                        Text(stringResource(R.string.go))
                    }
                },
                title = {
                    Text(
                        stringResource(R.string.congratulations),
                        style = MaterialTheme.typography.bodyLarge
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
                        Text(stringResource(R.string.message_successfully))
                        val result = viewModel.results

                    }
                }
            )
        }
        AndroidViewBinding(TestViewBinding::inflate, modifier = Modifier.fillMaxSize()) {
            question.text = currentQuestion
            btnConfirm.setOnClickListener {
                if (radioGroup.checkedRadioButtonId != -1)
                    viewModel.onQuestionAnswered(
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
