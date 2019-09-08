package br.com.fiap.trabalhofinal.model

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("name") val nome: String = "",
    @SerializedName("email") val email: String = ""
)