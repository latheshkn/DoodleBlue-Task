package com.example.doodlebluetask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.doodlebluetask.repository.CryptoCurrencyRepository

class ViewModelFactory(private val repository: CryptoCurrencyRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CryptoCurrencyViewModel::class.java)) {
            CryptoCurrencyViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}