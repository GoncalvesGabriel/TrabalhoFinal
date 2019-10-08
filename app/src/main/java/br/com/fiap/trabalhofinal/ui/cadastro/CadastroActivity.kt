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

    var produto: Produto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        if (this.intent.hasExtra("PRODUTO")) {
            produto = this.intent.getParcelableExtra<Produto?>("PRODUTO")
        }

        if (produto != null) {
            inputCodigo.setText(produto?.codigo)
            inputNome.setText(produto?.nome)
            inputQtdeEstoque.setText(produto?.qtdeEstoque.toString())
            inputValor.setText(produto?.valor.toString())
        }

        btCadastrar.setOnClickListener {
            produto = Produto(
                produto?.id,
                inputCodigo.text.toString(),
                inputNome.text.toString(),
                inputQtdeEstoque.text.toString().toDouble(),
                inputValor.text.toString().toDouble()
            )

            val insert = productViewModel.insert(produto)
            insert.invokeOnCompletion {
                val returnIntent = Intent()
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        }
    }
}
