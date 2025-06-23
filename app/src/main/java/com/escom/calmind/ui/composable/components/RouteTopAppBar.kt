package com.escom.calmind.ui.composable.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.escom.calmind.R
import com.escom.calmind.model.TopBarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteTopAppBar(title: TopBarTitle, onNavigateBack: () -> Unit) {
    TopAppBar(
        title = {
            Column {
                with(title) {
                    Text(text = stringResource(titleId), style = MaterialTheme.typography.titleMedium)
                    Text(text = username, style = MaterialTheme.typography.bodyLarge)
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = stringResource(R.string.go_back)
                )
            }
        }
    )
}