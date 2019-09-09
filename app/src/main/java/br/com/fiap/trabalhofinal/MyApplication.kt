package br.com.fiap.trabalhofinal

import android.app.Application
import br.com.fiap.trabalhofinal.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@MyApplication)
            // declare modules
            modules(
                listOf(
                    securityModule,
                    connectionModule,
                    repositoryModule,
                    uiModule,
                    viewModelModule
                )
            )
        }
    }
}