package br.com.fiap.trabalhofinal.model

import com.google.gson.annotations.SerializedName

class Produto (

    @SerializedName("id")val id: Long?,

    @SerializedName("codigo")val codigo: String = "",

    @SerializedName("nome")val nome: String = "",

    @SerializedName("qtdeEstoque")val qtdeEstoque: Double,

    @SerializedName("valor")val valor: Double
)
