package com.escom.calmind.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.escom.calmind.model.UserData
import com.escom.calmind.repository.UserDataRepository
import jakarta.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepositoryImpl @Inject constructor() : UserDataRepository {

    private val data: MutableLiveData<UserData> = MutableLiveData()

    override fun set(value: UserData) {
        this.data.value = value
    }

    override fun get(): LiveData<UserData> = this.data
}