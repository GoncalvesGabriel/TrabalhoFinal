package br.com.fiap.trabalhofinal.repository

import androidx.lifecycle.LiveData
import br.com.fiap.trabalhofinal.model.Produto

interface ProductRepository {

    fun findAll(): LiveData<List<Produto>>

    fun findProduct(
        id: Long,
        date: String,
        onComplete: (Produto?) -> Unit,
        onError: (Throwable?) -> Unit)

    suspend fun insert(
        produto: Produto,
        onComplete: (Produto?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun delete(
        produto: Produto,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}