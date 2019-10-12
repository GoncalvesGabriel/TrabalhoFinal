package br.com.fiap.trabalhofinal.repository

import br.com.fiap.trabalhofinal.model.Produto

interface ProductRepository {

    fun findAll(
        onComplete: (List<Produto>?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun findProduct(
        id: Long,
        date: String,
        onComplete: (Produto?) -> Unit,
        onError: (Throwable?) -> Unit)

    fun insert(
        produto: Produto?,
        onComplete: (Produto?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun delete(
        produto: Produto,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun checkStatus(
        onComplete: (Unit?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}