package com.escom.calmind.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopBarTitle(
    @StringRes val titleId: Int,
    val username: String
) : Parcelable
