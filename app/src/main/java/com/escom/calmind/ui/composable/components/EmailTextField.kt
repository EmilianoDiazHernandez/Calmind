package com.escom.calmind.ui.composable.components

import android.util.Patterns
/*import androidx.compose.foundation.text.KeyboardActions*/
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.escom.calmind.R

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    /*keyboardActions: KeyboardActions = KeyboardActions {}*/
) {
    OutlinedTextField(
        value = email, onValueChange = onEmailChange, label = {
            Text(
                stringResource(R.string.email),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        keyboardOptions = keyboardOptions,
        /*keyboardActions = keyboardActions,*/
        singleLine = true,
        isError = email.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    )
}