package br.com.fiap.trabalhofinal.model.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckStatusViewModel(
    val repository: ProductRepository
) : ViewModel()
{
    var success = false
    var fail = false;
    val messageResponse = MutableLiveData<String>()

    fun checkStatus(context: Context) {
        messageResponse.value = context.getString(R.string.connection_message)
        viewModelScope.launch(Dispatchers.IO) {
            repository.checkStatus(
                onComplete = {
                    success = true
                    messageResponse.value = context.getString(R.string.connection_sucess)
                },
                onError = {
                    fail = true
                    messageResponse.value = it?.message
                }
            )
        }
    }
}