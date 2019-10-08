package br.com.fiap.trabalhofinal.model.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.fiap.trabalhofinal.model.Produto
import br.com.fiap.trabalhofinal.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application, val repository: ProductRepository) : AndroidViewModel(application) {

    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()

    val allProduct: LiveData<List<Produto>> = repository.findAll();

    fun insert(produto: Produto?) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(
            produto,
            onComplete = {
                isLoading.value = false
                messageResponse.value = "Dados inseridos com sucesso"
            },
            onError = {
                isLoading.value = false
                messageResponse.value = it?.message
            }
        )
    }
}