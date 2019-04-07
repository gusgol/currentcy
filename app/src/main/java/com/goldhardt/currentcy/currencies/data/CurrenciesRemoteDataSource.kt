package com.goldhardt.currentcy.currencies.data

import com.goldhardt.core.data.Result
import com.goldhardt.core.data.currency.Currency
import com.goldhardt.core.data.currency.CurrencyService
import com.goldhardt.core.utils.safeApiCall
import java.io.IOException

class CurrenciesRemoteDataSource(private val service: CurrencyService) {

    suspend fun getCurrencies() = safeApiCall(
            call = { requestCurrencies() },
            errorMessage = "Error getting currencies from Banco Central do Brasil"
    )

    private suspend fun requestCurrencies(): Result<List<Currency>> {
        val response = service.getCurrencies().await()
        if (response.isSuccessful) {
            val body =  response.body()
            if (body != null) {
                return Result.Success(body.value)
            }
        }
        return Result.Error(
                IOException("Error getting BCB data ${response.code()} ${response.message()}")
        )
    }
}