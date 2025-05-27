package com.escom.calmind.ui.composable.welcome

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.escom.calmind.R

@Composable
fun SecondPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        val hobbies = stringArrayResource(R.array.hobbies)
        val selectedHobbies = remember {
            mutableStateListOf<String>()
        }
        val focusManager = LocalFocusManager.current
        var name by remember { mutableStateOf(String()) }
        OutlinedTextField(
            value = name, onValueChange = { name = it },
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
                imeAction = androidx.compose.ui.text.input.ImeAction.Done,
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
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
            hobbies.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .selectable(
                            selected = selectedHobbies.contains(it),
                            onClick = {
                                if (selectedHobbies.contains(it))
                                    selectedHobbies.remove(it)
                                else
                                    selectedHobbies.add(it)
                            },
                            role = Role.RadioButton
                        )
                ) {
                    Checkbox(
                        checked = selectedHobbies.contains(it),
                        onCheckedChange = { checked ->
                            if (checked) selectedHobbies.add(it)
                            else selectedHobbies.remove(it)
                        }
                    )
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    stringResource(R.string.get_started),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

