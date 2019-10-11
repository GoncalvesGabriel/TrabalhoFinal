package br.com.fiap.trabalhofinal.listener

import br.com.fiap.trabalhofinal.model.Produto

interface OnClickProdutoItemListener {
    fun onEditClicked(produto: Produto)

    fun onDeleteClicked(produto: Produto)
}