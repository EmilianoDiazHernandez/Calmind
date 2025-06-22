package com.escom.calmind.model

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class TopBarTitle(
    @StringRes val titleId: Int,
    val username: String
)
