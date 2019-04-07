package com.goldhardt.currentcy.currencies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.goldhardt.currentcy.currencies.data.CurrenciesRepository

class CurrenciesViewModelFactory constructor(
        private val repository: CurrenciesRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrenciesViewModel::class.java)) {
            return CurrenciesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}