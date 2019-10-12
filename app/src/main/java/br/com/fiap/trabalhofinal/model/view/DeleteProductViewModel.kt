package br.com.fiap.trabalhofinal.model.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.trabalhofinal.model.Produto
import br.com.fiap.trabalhofinal.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteProductViewModel(
    val repository: ProductRepository
) : ViewModel()
{
    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()

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