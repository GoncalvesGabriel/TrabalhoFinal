package br.com.fiap.trabalhofinal.model.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.fiap.trabalhofinal.model.Produto
import br.com.fiap.trabalhofinal.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application, val repository: ProductRepository) : AndroidViewModel(application) {

    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()

    val allProducts: MutableLiveData<List<Produto>> = MutableLiveData()

    fun getProducts() {
        isLoading.value = true
        repository.findAll({
            allProducts.value = it
            messageResponse.value = ""
            isLoading.value = false
        }, {
            allProducts.value = emptyList()
            messageResponse.value = it?.message
            isLoading.value = false
        })
    }

    fun delete(produto: Produto) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(
            produto,
            onComplete = {
                isLoading.value = false
                messageResponse.value = "Dado excluído com sucesso"
            },
            onError = {
                isLoading.value = false
                messageResponse.value = it?.message
            }
        )
    }

}