package com.alesh.currencyconverter.di.module

import com.alesh.data.repository.CurrenciesRepositoryImpl
import com.alesh.data.source.remote.CurrenciesApi
import com.alesh.domain.repository.CurrenciesRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoriesModule {

    @Provides
    fun provideCurrenciesRepository(
        api: CurrenciesApi
    ): CurrenciesRepository = CurrenciesRepositoryImpl(api)

}