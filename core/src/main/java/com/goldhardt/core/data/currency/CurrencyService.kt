package com.goldhardt.core.data.currency

import com.goldhardt.core.data.api.BCBResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyService {

    @GET("Moedas")
    fun getCurrencies(): Deferred<Response<BCBResponse<Currency>>>
}