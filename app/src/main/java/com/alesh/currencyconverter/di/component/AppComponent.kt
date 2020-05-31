package com.alesh.currencyconverter.di.component

import android.app.Application
import com.alesh.currencyconverter.di.module.ApiModule
import com.alesh.currencyconverter.di.module.RepositoriesModule
import com.alesh.currencyconverter.ui.currencies.CurrenciesViewModel
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ApiModule::class, RepositoriesModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    val currenciesViewModel: CurrenciesViewModel
}