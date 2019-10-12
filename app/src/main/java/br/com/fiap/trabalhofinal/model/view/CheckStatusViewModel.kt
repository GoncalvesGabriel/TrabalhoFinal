package br.com.fiap.trabalhofinal.model.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.trabalhofinal.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckStatusViewModel(
    val repository: ProductRepository
) : ViewModel()
{
    var success = false
    val messageResponse = MutableLiveData<String>()

    fun checkStatus() {
        messageResponse.value = "Estabelecendo conexão com o servidor"
        viewModelScope.launch(Dispatchers.IO) {
            repository.checkStatus(
                onComplete = {
                    success = true
                    messageResponse.value = "Conexão realizada com sucesso"
                },
                onError = {
                    messageResponse.value = it?.message
                }
            )
        }
    }
}