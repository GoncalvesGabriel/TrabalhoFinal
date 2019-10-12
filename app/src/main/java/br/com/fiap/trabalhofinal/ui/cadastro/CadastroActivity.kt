package br.com.fiap.trabalhofinal.ui.cadastro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.model.Produto
import br.com.fiap.trabalhofinal.model.view.FormProductViewModel
import kotlinx.android.synthetic.main.activity_cadastro.*
import org.koin.android.viewmodel.ext.android.viewModel

class CadastroActivity : AppCompatActivity() {

    val formProductViewModel: FormProductViewModel by viewModel()

    lateinit var produto: Produto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        produto = Produto()

        if (this.intent.hasExtra("PRODUTO")) {
            produto = this.intent.getParcelableExtra("PRODUTO")
            inputCodigo.setText(produto.codigo)
            inputNome.setText(produto.nome)
            inputQtdeEstoque.setText(produto.qtdeEstoque.toString())
            inputValor.setText(produto.valor.toString())
        }

        formProductViewModel.messageResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        btCadastrar.setOnClickListener {
            produto = Produto(
                produto.id,
                inputCodigo.text.toString(),
                inputNome.text.toString(),
                inputQtdeEstoque.text.toString().toDouble(),
                inputValor.text.toString().toDouble()
            )

            formProductViewModel.insert(produto)

            formProductViewModel.isLoading.observe(this, Observer {
                if(!it) {
                    val returnIntent = Intent()
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                }
            })
        }
    }

}
