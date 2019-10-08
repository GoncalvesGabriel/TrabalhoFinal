package br.com.fiap.trabalhofinal.services

import br.com.fiap.trabalhofinal.model.Produto
import retrofit2.Call
import retrofit2.http.*

interface ProdutoService {

    @POST("/produtos")
    fun insert(@Body produto: Produto?): Call<Produto>

    @GET("/produtos/{id}/{data}")
    fun findProdutos(@Path("id") id: Long, @Path("data") date: String): Call<Produto>

    @GET("/produtos/findAll")
    fun findAll(): Call<List<Produto>>

    @DELETE("/produtos/{id}")
    fun delete(@Path("id") id: Long): Call<Unit>
}

