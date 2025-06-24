package com.escom.calmind.ui.composable

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.escom.calmind.databinding.TestViewBinding
import com.escom.calmind.ui.theme.CalmindTheme


@Composable
fun TestScreen(
    modifier: Modifier = Modifier,
    currentQuestion: String,
    onQuestionAnswered: (answer: Int) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        AndroidViewBinding(
            factory = { inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean ->
                TestViewBinding.inflate(inflater, parent, attachToParent).apply {
                    radioGroup.setOnCheckedChangeListener { _, checkedId ->
                        btnConfirm.isEnabled = checkedId != -1
                    }
                    btnConfirm.setOnClickListener {
                        onQuestionAnswered(
                            radioGroup.indexOfChild(
                                radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                            )
                        )
                        radioGroup.clearCheck()
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        ) {
            question.text = currentQuestion
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
                onQuestionAnswered = {}
            )
        }
    }
}
