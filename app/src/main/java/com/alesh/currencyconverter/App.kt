package com.alesh.currencyconverter

import android.app.Application
import com.alesh.currencyconverter.di.component.AppComponent
import com.alesh.currencyconverter.di.component.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var component: AppComponent
            private set
    }
}