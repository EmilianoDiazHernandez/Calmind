package com.escom.calmind.ui.composable

import android.widget.RadioButton
import com.escom.calmind.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.hilt.navigation.compose.hiltViewModel
import com.escom.calmind.databinding.TestViewBinding
import com.escom.calmind.viewmodel.StressQuestionsViewModel
import android.widget.Toast


@Composable
fun TestScreen() {

    val viewModel = hiltViewModel<StressQuestionsViewModel>()

    val currentQuestion by viewModel.currentQuestion.observeAsState("")

    AndroidViewBinding(TestViewBinding::inflate) {
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