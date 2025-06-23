package com.escom.calmind.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BorderColor
import androidx.compose.material.icons.outlined.FindInPage
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escom.calmind.R
import com.escom.calmind.model.UserData
import com.escom.calmind.ui.composable.components.CardButton
import com.escom.calmind.ui.theme.CalmindTheme


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    userData: UserData,
    onClickGratitudeJournalScreen: () -> Unit,
    onClickMeditationScreen: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {}) {
            Icon(imageVector = Icons.Outlined.FindInPage, contentDescription = null)
            Text(stringResource(R.string.look_for_an_article))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    stringResource(R.string.gratitude_journal_description),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Justify
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(imageVector = Icons.Outlined.Star, contentDescription = null)
                    Text(
                        stringResource(R.string.progress, userData.gratitudeDays),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CardButton(
                        modifier = Modifier.fillMaxHeight(),
                        onClick = onClickGratitudeJournalScreen,
                        imageVector = Icons.Outlined.BorderColor,
                        painterResourceId = R.drawable.meditation
                    ) {
                        Text(
                            text = stringResource(R.string.gratitude_journal),
                            maxLines = 2,
                            textAlign = TextAlign.Center
                        )
                    }
                    CardButton(
                        modifier = Modifier.fillMaxHeight(),
                        onClick = onClickMeditationScreen,
                        imageVector = Icons.Outlined.Lightbulb,
                        painterResourceId = R.drawable.meditation
                    ) {
                        Text(
                            text = stringResource(R.string.meditation),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    device = "spec:width=1080px,height=2340px,dpi=440", showSystemUi = true,
    showBackground = true
)
@Composable
fun MainScreenPreview() {
    CalmindTheme {
        MainScreen(
            userData = UserData(
                name = "Diego",
                age = 14,
                hobbies = emptyList(),
                schooling = "Secondary School",
                gratitudeDays = 15
            ),
            onClickGratitudeJournalScreen = {},
            onClickMeditationScreen = {}
        )
    }
}
