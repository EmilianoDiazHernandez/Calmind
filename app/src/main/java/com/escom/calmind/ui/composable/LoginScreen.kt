package com.escom.calmind.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escom.calmind.R
import com.escom.calmind.ui.composable.components.EmailTextField
import com.escom.calmind.ui.composable.components.PasswordTextField
import com.escom.calmind.ui.shape.semiCircleShape
import com.escom.calmind.ui.theme.CalmindTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column {
            Box(
                modifier = Modifier
                    .align(Alignment.Start)
                    .clip(CircleShape)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.get_started),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Image(painter = painterResource(R.drawable.logo), contentDescription = null)
                }
            }
            EmailTextField(email = email, onEmailChange = onEmailChange)
            PasswordTextField(
                password = password,
                onPasswordChange = onPasswordChange,
                isError = false
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    CalmindTheme {
        LoginScreen(
            modifier = Modifier,
            email = "",
            onEmailChange = {},
            password = "",
            onPasswordChange = {}
        )
    }
}