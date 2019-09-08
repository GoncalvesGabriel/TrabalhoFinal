package br.com.fiap.trabalhofinal.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val securityModule = module {
    single {
        FirebaseAuth.getInstance()
    }
}