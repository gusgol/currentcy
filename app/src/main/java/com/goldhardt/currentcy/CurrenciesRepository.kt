package com.goldhardt.currentcy

import com.goldhardt.core.Currency
import com.goldhardt.core.Result

class CurrenciesRepository(private val remoteDataSource: CurrenciesRemoteDataSource) {
    suspend fun getCurrencies(): Result<List<Currency>> {
        return remoteDataSource.getCurrencies()
    }
}