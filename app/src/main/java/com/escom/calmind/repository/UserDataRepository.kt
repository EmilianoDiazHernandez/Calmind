package com.escom.calmind.repository

import androidx.lifecycle.LiveData
import com.escom.calmind.model.UserData

interface UserDataRepository {
    fun addData(data: UserData)
    fun getData(): LiveData<UserData>
}