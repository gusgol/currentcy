package com.goldhardt.currentcy.currencies.data

import com.goldhardt.core.data.Result
import com.goldhardt.core.data.currency.Currency

class CurrenciesRepository(private val remoteDataSource: CurrenciesRemoteDataSource) {
    suspend fun getCurrencies(): Result<List<Currency>> {
        return remoteDataSource.getCurrencies()
    }
}