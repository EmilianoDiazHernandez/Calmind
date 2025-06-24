package com.escom.calmind.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.widget.doOnTextChanged
import com.escom.calmind.databinding.GratitudeJournalBinding
import com.escom.calmind.ui.theme.CalmindTheme
import com.escom.calmind.utils.MIN_LENGTH_GRATITUDE_JOURNAL

@Composable
fun GratitudeJournalScreen(
    modifier: Modifier = Modifier,
    journalText: String,
    onJournalTextChange: (String) -> Unit,
    onJournalTextSend: () -> Unit
) {
    AndroidViewBinding(modifier = modifier, factory = GratitudeJournalBinding::inflate) {
        editTextJournal.setText(journalText)
        editTextJournal.doOnTextChanged { newText: CharSequence?, _, _, _ ->
            newText?.let {
                onJournalTextChange(it.toString())
            }
        }
        btnSend.isEnabled = journalText.length >= MIN_LENGTH_GRATITUDE_JOURNAL
        btnSend.setOnClickListener {
            onJournalTextSend()
        }
    }
}

@Preview
@Composable
fun GratitudeJournalPreview() {
    CalmindTheme {
        GratitudeJournalScreen(
            journalText = "I'm happy",
            onJournalTextChange = {}
        ) {}
    }
}
