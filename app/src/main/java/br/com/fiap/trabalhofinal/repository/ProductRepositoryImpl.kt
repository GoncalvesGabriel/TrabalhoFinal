package br.com.fiap.trabalhofinal.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.trabalhofinal.model.Produto
import br.com.fiap.trabalhofinal.services.ProdutoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductRepositoryImpl(private val service: ProdutoService) : ProductRepository {

    override fun findAll(): LiveData<List<Produto>> {
        val newsData = MutableLiveData<List<Produto>>()
        service.findAll().enqueue(object : Callback<List<Produto>> {
            override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
                newsData.setValue(null);
            }

            override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }
        });
        return newsData;
    }

    override fun findProduct(
        id: Long, date: String,
        onComplete: (Produto?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {

        service.findProdutos(id, date)
            .enqueue(object : Callback<Produto> {
                override fun onFailure(call: Call<Produto>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Produto>, response: Response<Produto>) {
                    if (response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Não foi possivel realizar a busca"))
                    }
                }
            }
            )
    }

    @WorkerThread
    override suspend fun insert(
        produto: Produto,
        onComplete: (Produto?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        service.insert(produto)
            .enqueue(object : Callback<Produto> {
                override fun onResponse(call: Call<Produto>, response: Response<Produto>) {
                    if (response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Não foi possivel inserir o produto"))
                    }
                }

                override fun onFailure(call: Call<Produto>, t: Throwable) {
                    onError(Throwable("Não foi possivel inserir o produto"))
                }
            })
    }

    @WorkerThread
    override fun delete(
        produto: Produto,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        val id: Long? = produto.id
        if (id != null) {
            service.delete(id)
                .enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if (response.isSuccessful) {
                            onComplete("Registro excluído com sucesso")
                        } else {
                            onError(Throwable("Não foi possivel inserir o produto"))
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        onError(Throwable("Não foi possivel excluir o produto"))
                    }
                })
        }
    }
}