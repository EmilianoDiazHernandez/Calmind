package com.escom.calmind.ui.composable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.widget.addTextChangedListener
import com.escom.calmind.R
import com.escom.calmind.databinding.GratitudeJournalBinding
import com.escom.calmind.model.EmotionResponse
import com.escom.calmind.ui.composable.components.EmotionItem
import com.escom.calmind.ui.theme.CalmindTheme
import com.escom.calmind.utils.MIN_LENGTH_GRATITUDE_JOURNAL

@Composable
fun GratitudeJournalScreen(
    modifier: Modifier = Modifier,
    journalText: String,
    onJournalTextChange: (String) -> Unit,
    onJournalTextSend: () -> Unit,
    emotions: List<EmotionResponse>,
    onAcceptEmotionsDialog: () -> Unit,
    isLoading: Boolean
) {
    var textValue by remember { mutableStateOf(journalText) }
    if (isLoading) {
        Dialog(properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ), onDismissRequest = {}) {
            LoadingFullScreen(modifier = Modifier.padding(vertical = 24.dp))
        }
    }
    else if (emotions.isNotEmpty()) {
        Dialog(
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            onDismissRequest = { onAcceptEmotionsDialog() }
        ) {
            Surface {
                Column(modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()) {
                    Text(
                        stringResource(R.string.detected),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(all = 8.dp)
                    ) {
                        items(emotions) {
                            Card(
                                modifier = Modifier
                                    .height(IntrinsicSize.Max)
                                    .padding(horizontal = 8.dp)
                            ) {
                                EmotionItem(
                                    emotionResponse = it,
                                    modifier = Modifier.padding(12.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    AndroidViewBinding(
        modifier = modifier,
        factory = { inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean ->
            GratitudeJournalBinding.inflate(inflater, parent, attachToParent).apply {
                editTextJournal.addTextChangedListener { editable ->
                    val newValue = editable?.toString().orEmpty()
                    textValue = newValue
                    onJournalTextChange(newValue)
                }
                btnSend.setOnClickListener {
                    onJournalTextSend()
                }
            }
        },
        update = {
            if (editTextJournal.text.toString() != textValue)
                editTextJournal.setText(textValue)
            btnSend.isEnabled = journalText.length >= MIN_LENGTH_GRATITUDE_JOURNAL
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GratitudeJournalPreview() {
    CalmindTheme {
        GratitudeJournalScreen(
            journalText = "I'm happy",
            onJournalTextChange = {},
            emotions = emptyList(),
            onAcceptEmotionsDialog = {},
            modifier = Modifier,
            onJournalTextSend = {},
            isLoading = false
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GratitudeJournalPreview2() {
    CalmindTheme {
        GratitudeJournalScreen(
            journalText = "I'm happy",
            onJournalTextChange = {},
            emotions = listOf(
                EmotionResponse(emotion = "love", trust = 0.8287402391433716),
                EmotionResponse(emotion = "sadness", trust = 0.3353300988674164),
                EmotionResponse(emotion = "desire", trust = 0.27142348885536194)
            ),
            onAcceptEmotionsDialog = {},
            modifier = Modifier,
            onJournalTextSend = {},
            isLoading = false
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GratitudeJournalPreview3() {
    CalmindTheme {
        GratitudeJournalScreen(
            journalText = "I'm happy",
            onJournalTextChange = {},
            emotions = listOf(
                EmotionResponse(emotion = "love", trust = 0.8287402391433716),
                EmotionResponse(emotion = "sadness", trust = 0.3353300988674164),
                EmotionResponse(emotion = "desire", trust = 0.27142348885536194)
            ),
            onAcceptEmotionsDialog = {},
            modifier = Modifier,
            onJournalTextSend = {},
            isLoading = true
        )
    }
}
