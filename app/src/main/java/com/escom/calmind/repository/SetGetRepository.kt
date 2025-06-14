package com.escom.calmind.repository

import androidx.lifecycle.LiveData

interface SetGetRepository<T> {
    fun set(value: T)
    fun get(): LiveData<T>
}
