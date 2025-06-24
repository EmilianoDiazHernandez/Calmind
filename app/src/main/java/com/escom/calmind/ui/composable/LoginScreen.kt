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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escom.calmind.R
import com.escom.calmind.ui.composable.components.CardGenericError
import com.escom.calmind.ui.composable.components.EmailTextField
import com.escom.calmind.ui.composable.components.PasswordTextField
import com.escom.calmind.ui.theme.CalmindTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    isError: Boolean,
    badCredentials: Boolean
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Start)
                    .clip(CircleShape)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .background(Color.White)
                    .padding(all = 12.dp)
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
            Spacer(modifier = Modifier.height(8.dp))
            EmailTextField(
                email = email,
                onEmailChange = onEmailChange,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                isError = badCredentials
            )
            val keyboardController = LocalSoftwareKeyboardController.current
            PasswordTextField(
                password = password,
                onPasswordChange = onPasswordChange,
                isError = badCredentials,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        keyboardController?.hide()
                        onLogin()
                    }
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { keyboardController?.hide(); onLogin() }, enabled = !isLoading) {
                if (isLoading)
                    CircularProgressIndicator()
                else
                    Text(text = stringResource(R.string.sing_in))
            }
            CardGenericError(
                visible = isError || badCredentials,
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = stringResource(if (isError) R.string.unknown_error else R.string.bad_credentials),
                    modifier = Modifier.padding(16.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    CalmindTheme {
        LoginScreen(
            modifier = Modifier,
            isLoading = false,
            email = "",
            onEmailChange = {},
            password = "",
            onPasswordChange = {},
            onLogin = {},
            isError = false,
            badCredentials = false
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview2() {
    CalmindTheme {
        LoginScreen(
            modifier = Modifier,
            isLoading = false,
            email = "",
            onEmailChange = {},
            password = "",
            onPasswordChange = {},
            onLogin = {},
            isError = true,
            badCredentials = true
        )
    }
}