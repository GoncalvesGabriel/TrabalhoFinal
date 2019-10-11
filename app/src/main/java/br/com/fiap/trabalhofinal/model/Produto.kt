package br.com.fiap.trabalhofinal.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Produto (
    @SerializedName("id")var id: Long = 0L,
    @SerializedName("codigo")var codigo: String = "",
    @SerializedName("nome")var nome: String = "",
    @SerializedName("qtdeEstoque")var qtdeEstoque: Double = 0.0,
    @SerializedName("valor")var valor: Double = 0.0
) : Parcelable
