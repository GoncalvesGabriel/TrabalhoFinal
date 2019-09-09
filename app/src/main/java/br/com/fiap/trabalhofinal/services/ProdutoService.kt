package br.com.fiap.trabalhofinal.services

import androidx.lifecycle.LiveData
import br.com.fiap.trabalhofinal.model.Produto
import retrofit2.Call
import retrofit2.http.*

interface ProdutoService {

    @POST("/produtos/")
    fun insert(@Body produto: Produto)

    @GET("/produtos/{id}/{data}")
    fun findProdutos(@Path("id") id: Long, @Path("data") date: String): Call<Produto>

    @GET("/produtos/findAll")
    fun findAll():LiveData<List<Produto>>

    @DELETE("/produtos/")
    fun delete(@Body id: Long);
}

