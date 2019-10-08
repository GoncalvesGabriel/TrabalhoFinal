package br.com.fiap.trabalhofinal.di

import br.com.fiap.trabalhofinal.model.view.ProductViewModel
import br.com.fiap.trabalhofinal.repository.ProductRepository
import br.com.fiap.trabalhofinal.repository.ProductRepositoryImpl
import br.com.fiap.trabalhofinal.services.ProdutoService
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val securityModule = module {
    single {
        FirebaseAuth.getInstance()
    }
}

val connectionModule = module {
    single {
            Retrofit.Builder()
                .baseUrl("https://trabalho-final-mobile.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addNetworkInterceptor(StethoInterceptor())
                        .build())
                .build()
        }

    single {
        get<Retrofit>().create(ProdutoService::class.java)
    }
}

val repositoryModule = module {
    factory<ProductRepository> { ProductRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel {
        ProductViewModel(get(), get())
    }
}
