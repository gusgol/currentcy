package com.goldhardt.core

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyService {

    @GET("Moedas")
    fun getCurrencies(): Deferred<Response<BCBResponse<Currency>>>
}