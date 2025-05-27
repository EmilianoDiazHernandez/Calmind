package com.escom.calmind.ui.composable.welcome

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escom.calmind.R
import com.escom.calmind.ui.theme.CalmindTheme

@Composable
fun SecondPage(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    selectedHobbies: List<String>,
    onCheckHobby: (String) -> Unit,
    onUncheckHobby: (String) -> Unit,
    schooling: String,
    onChangeSchooling: (String) -> Unit,
    age: String,
    onAgeChange: (String) -> Unit,
    onClickStartButton: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .focusable()
            .padding(16.dp)
    ) {
        val ageError = age.isNotBlank() && age.toIntOrNull()?.let { it <= 0 } ?: true
        val focusManager = LocalFocusManager.current
        //val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            value = name, onValueChange = onNameChange,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface
            ),
            label = {
                Text(
                    stringResource(R.string.what_is_your_name),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            singleLine = true, modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = age, onValueChange = onAgeChange,
            isError = ageError,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface
            ),
            label = {
                Text(
                    stringResource(R.string.how_older_you),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            singleLine = true, modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            })
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            stringResource(R.string.what_are_your_hobbies),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup()
        ) {
            val hobbies = stringArrayResource(R.array.hobbies)
            hobbies.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedHobbies.contains(it),
                            onClick = {
                                if (selectedHobbies.contains(it))
                                    onUncheckHobby(it)
                                //onCheckHobby(it)
                                //selectedHobbies.remove(it)
                                else
                                    onCheckHobby(it)
                                //selectedHobbies.add(it)
                            },
                            role = Role.Checkbox
                        )
                ) {
                    Checkbox(
                        checked = selectedHobbies.contains(it),
                        onCheckedChange = null
                    )
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                stringResource(R.string.schooling),
                style = MaterialTheme.typography.titleMedium
            )
            val schoolingArray = stringArrayResource(R.array.schooling_array)
            schoolingArray.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (it == schooling),
                            onClick = { onChangeSchooling(it) },
                            role = Role.RadioButton
                        )
                ) {
                    RadioButton(selected = (it == schooling), onClick = null)
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
        Button(
            onClick = onClickStartButton,
            enabled = name.isNotBlank() && selectedHobbies.isNotEmpty()
                    && schooling.isNotEmpty() && age.isNotBlank() && !ageError,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                stringResource(R.string.get_started),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview(device = "id:Nexus 5")
@Composable
private fun SecondPagePreview() {
    CalmindTheme {
        Surface {
            SecondPage(
                modifier = Modifier,
                name = "Diego",
                onNameChange = {},
                selectedHobbies = listOf("Drawing", "Sports", "Swimming"),
                onCheckHobby = {},
                onUncheckHobby = {},
                schooling = "Secondary School",
                onChangeSchooling = {},
                age = "8",
                onAgeChange = {},
                onClickStartButton = {}
            )
        }
    }
}

