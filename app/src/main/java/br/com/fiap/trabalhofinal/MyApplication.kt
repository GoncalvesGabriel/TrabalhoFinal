package br.com.fiap.trabalhofinal

import android.app.Application
import android.content.Context
import br.com.fiap.trabalhofinal.di.connectionModule
import br.com.fiap.trabalhofinal.di.repositoryModule
import br.com.fiap.trabalhofinal.di.securityModule
import br.com.fiap.trabalhofinal.di.viewModelModule
import br.com.fiap.trabalhofinal.helper.LocaleHelper
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
                    viewModelModule
                )
            )
        }
    }

    override fun attachBaseContext(base: Context) {
        val defaultLanguage = if (LocaleHelper.getLanguage(base).isNullOrBlank())  "pt-brb" else LocaleHelper.getLanguage(base)!!
        super.attachBaseContext(LocaleHelper.onAttach(base, defaultLanguage))
    }
}