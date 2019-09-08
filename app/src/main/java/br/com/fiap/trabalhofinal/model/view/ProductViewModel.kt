package br.com.fiap.trabalhofinal.model.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.fiap.trabalhofinal.model.Produto
import br.com.fiap.trabalhofinal.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application, val repository: ProductRepository) : AndroidViewModel(application) {

    val allWords: LiveData<List<Produto>> = repository.findAll();

    fun insert(word: Produto) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}