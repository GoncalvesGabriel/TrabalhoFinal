package br.com.fiap.trabalhofinal.ui.cadastro

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.model.Produto
import br.com.fiap.trabalhofinal.model.view.ProductViewModel
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_cadastro.inputNome
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.android.viewmodel.ext.android.viewModel

class CadastroActivity : AppCompatActivity() {

    val productViewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btCadastrar.setOnClickListener {
            val produto = Produto(
                codigo = inputCodigo.text.toString(),
                nome = inputNome.toString(),
                qtdeEstoque = inputQtdeEstoque.text.toString().toDouble(),
                valor = inputValor.text.toString().toDouble(),
                id = null
            )
            productViewModel.insert(produto).invokeOnCompletion {
                Toast.makeText(this, "Produto cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                val returnIntent = Intent()
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        }
    }
}
