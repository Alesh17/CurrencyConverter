package com.alesh.currencyconverter.di.module

import com.alesh.data.source.remote.CurrenciesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [HttpModule::class])
object ApiModule {

    @Provides
    fun provideAuthApiService(retrofit: Retrofit): CurrenciesApi =
        retrofit.create(CurrenciesApi::class.java)
}