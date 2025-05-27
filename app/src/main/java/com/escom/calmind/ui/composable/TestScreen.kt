package com.escom.calmind.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.escom.calmind.databinding.TestViewBinding
import com.escom.calmind.repository.impl.StressQuestionsRepositoryImpl
import com.escom.calmind.viewmodel.StressQuestionsViewModel
import com.escom.calmind.viewmodel.StressQuestionsViewModelFactory

@Composable
fun TestScreen() {
    /*val owner = LocalViewModelStoreOwner.current ?: return

    val repository = remember { StressQuestionsRepositoryImpl() }
    val factory = remember { StressQuestionsViewModelFactory(repository) }

    val viewModel: StressQuestionsViewModel = remember(owner, factory) {
        ViewModelProvider(owner, factory)[StressQuestionsViewModel::class.java]
    }*/
    val viewModel = hiltViewModel<StressQuestionsViewModel>()

    val question by viewModel.currentQuestion.observeAsState("")

    AndroidViewBinding(TestViewBinding::inflate) {
        Question.text = question
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            viewModel.onQuestionAnswered()
        }

    }
}