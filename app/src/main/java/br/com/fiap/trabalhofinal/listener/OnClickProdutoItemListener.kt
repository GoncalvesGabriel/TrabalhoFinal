package br.com.fiap.trabalhofinal.listener

import br.com.fiap.trabalhofinal.model.Produto

interface OnClickProdutoItemListener {
    fun onItemClicked(produto: Produto)
}