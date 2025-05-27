package com.escom.calmind.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.escom.calmind.R
import com.escom.calmind.ui.theme.CalmindTheme
import com.escom.calmind.utils.IS_FIRST_TIME
import com.escom.calmind.utils.dataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map

@Composable
fun SplashScreen(modifier: Modifier = Modifier, onNavigate: (isFirstTime: Boolean) -> Unit) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .border(
                        BorderStroke(2.dp, Color.Yellow),
                        CircleShape
                    )
                    .padding(2.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            CircularProgressIndicator()
        }
        val isFirstTimeKey = booleanPreferencesKey(IS_FIRST_TIME)
        val context = LocalContext.current
        LaunchedEffect(true) {
            context.dataStore.data.map { preferences ->
                preferences[isFirstTimeKey] ?: true
            }.collect {
                delay(2500L)
                onNavigate(it)
            }

        }
    }
}


@Preview(
    device = "spec:width=1080px,height=2424px,dpi=640", showBackground = true,
    showSystemUi = true
)
@Composable
private fun SplashScreenPreview() {
    CalmindTheme(darkTheme = false) {
        SplashScreen { }
    }
}
