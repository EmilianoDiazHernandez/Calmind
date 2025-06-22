package com.escom.calmind.ui.composable.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.escom.calmind.model.TopBarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteTopAppBar(title: TopBarTitle) {
    TopAppBar(
        title = {
            Column {
                with(title) {
                    Text(stringResource(titleId))
                    Text(username)
                }
            }
        },
        navigationIcon = {
            //IconButton() { }
        }
    )
}