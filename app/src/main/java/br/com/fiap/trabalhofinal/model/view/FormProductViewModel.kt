package br.com.fiap.trabalhofinal.model.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.trabalhofinal.model.Produto
import br.com.fiap.trabalhofinal.repository.ProductRepository

class FormProductViewModel(
    val productRepository: ProductRepository
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()

    fun insert(product: Produto) {
        isLoading.value = true
        productRepository.insert(
            product,
            onComplete = {
                isLoading.value = false
                messageResponse.value = "Produto inserido com sucesso"
            },
            onError = {
                isLoading.value = false
                messageResponse.value = it?.message
            }
        )
    }
}