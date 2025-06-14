package com.escom.calmind.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.escom.calmind.model.TestResult
import com.escom.calmind.repository.TestResultRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestResultRepositoryImpl @Inject constructor() : TestResultRepository {

    private val data: MutableLiveData<TestResult> = MutableLiveData()

    override fun set(value: TestResult) {
        data.value = value
    }

    override fun get(): LiveData<TestResult> = data
}