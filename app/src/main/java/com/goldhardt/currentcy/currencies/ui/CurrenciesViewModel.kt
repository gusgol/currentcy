package com.goldhardt.currentcy.currencies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldhardt.core.data.Result
import com.goldhardt.core.data.currency.Currency
import com.goldhardt.core.utils.SingleLiveEvent
import com.goldhardt.currentcy.currencies.data.CurrenciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrenciesViewModel(private val repository: CurrenciesRepository) : ViewModel() {

    private val _currencies = MutableLiveData<List<Currency>>()
    val currencies: LiveData<List<Currency>>
        get() = _currencies

    val error: SingleLiveEvent<Void> by lazy { SingleLiveEvent<Void>() }

    fun getCurrencies() {
        viewModelScope.launch(Dispatchers.Default) {
            val result = repository.getCurrencies()
            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    _currencies.value = result.data
                } else {
                    error.call()
                }
            }
        }
    }
}