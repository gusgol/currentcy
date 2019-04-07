package com.goldhardt.currentcy

import com.goldhardt.core.data.api.BCBApi
import com.goldhardt.core.data.currency.CurrencyService
import com.goldhardt.currentcy.currencies.data.CurrenciesRemoteDataSource
import com.goldhardt.currentcy.currencies.data.CurrenciesRepository
import com.goldhardt.currentcy.currencies.ui.CurrenciesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<CurrencyService> {
        BCBApi.get().create(CurrencyService::class.java)
    }

    single {
        CurrenciesRemoteDataSource(get())
    }

    single {
        CurrenciesRepository(get())
    }

    viewModel {
        CurrenciesViewModel(get())
    }
}