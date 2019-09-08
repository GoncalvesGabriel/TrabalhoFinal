package br.com.fiap.trabalhofinal.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import br.com.fiap.trabalhofinal.model.Produto
import br.com.fiap.trabalhofinal.services.ProdutoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepositoryImpl(private val service: ProdutoService): ProductRepository {

    override fun findAll(): LiveData<List<Produto>> {
        return service.findAll();
    }

    override fun findProduct( id: Long, date: String,
        onComplete:(Produto?) -> Unit,
        onError: (Throwable?) -> Unit) {

        service.findProdutos(id, date)
            .enqueue(object : Callback<Produto> {
                override fun onFailure(call: Call<Produto>, t: Throwable) {
                    onError(t)
                }
                override fun onResponse(call: Call<Produto>, response: Response<Produto>) {
                    if(response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Não foi possivel realizar a busca"))
                    }
                }
            }
        )
    }

    @WorkerThread
    override suspend fun insert(produto: Produto) {
        service.insert(produto)
    }
}