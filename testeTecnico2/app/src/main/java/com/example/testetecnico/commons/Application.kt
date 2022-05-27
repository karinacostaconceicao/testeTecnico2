package com.example.testetecnico.commons

import android.app.Application
import com.example.testetecnico.commons.koin.repositoryModule
import com.example.testetecnico.commons.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(repositoryModule + viewModelModule)
        }
    }
}