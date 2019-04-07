package com.goldhardt.currentcy

import com.goldhardt.core.Currency
import com.goldhardt.core.CurrencyService
import com.goldhardt.core.Result
import com.goldhardt.core.safeApiCall
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