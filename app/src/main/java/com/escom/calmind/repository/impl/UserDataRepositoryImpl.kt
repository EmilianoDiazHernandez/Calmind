package com.escom.calmind.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.escom.calmind.model.UserData
import com.escom.calmind.repository.UserDataRepository
import jakarta.inject.Inject

class UserDataRepositoryImpl @Inject constructor() : UserDataRepository {

    private val data: MutableLiveData<UserData> = MutableLiveData()

    override fun addData(data: UserData) {
        this.data.value = data
    }

    override fun getData(): LiveData<UserData> = this.data
}