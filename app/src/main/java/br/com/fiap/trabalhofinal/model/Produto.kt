package br.com.fiap.trabalhofinal.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Produto (
    @SerializedName("id")var id: Long?,
    @SerializedName("codigo")var codigo: String = "",
    @SerializedName("nome")var nome: String = "",
    @SerializedName("qtdeEstoque")var qtdeEstoque: Double,
    @SerializedName("valor")var valor: Double
) : Parcelable
