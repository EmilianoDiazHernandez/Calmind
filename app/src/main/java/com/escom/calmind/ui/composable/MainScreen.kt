package com.escom.calmind.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escom.calmind.R
import com.escom.calmind.model.UserData
import com.escom.calmind.ui.theme.CalmindTheme


@Composable
fun MainScreen(modifier: Modifier = Modifier, userData: UserData) {

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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(modifier = Modifier.clip(RectangleShape)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.BorderColor, contentDescription = null)
                        Text(stringResource(R.string.gratitude_journal))
                    }
                }
                Box(modifier = Modifier.clip(RectangleShape)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.Lightbulb, contentDescription = null)
                        Text(stringResource(R.string.meditation))
                    }
                }
            }
        }
    }
}

@Preview(
    device = "spec:width=1080px,height=2340px,dpi=440,isRound=true", showSystemUi = true,
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
            )
        )
    }
}
