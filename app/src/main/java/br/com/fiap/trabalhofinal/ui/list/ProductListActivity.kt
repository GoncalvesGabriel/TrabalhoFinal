package br.com.fiap.trabalhofinal.ui.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.adapter.ProductListAdapter
import br.com.fiap.trabalhofinal.listener.OnClickProdutoItemListener
import br.com.fiap.trabalhofinal.model.Produto
import br.com.fiap.trabalhofinal.model.view.DeleteProductViewModel
import br.com.fiap.trabalhofinal.model.view.ProductViewModel
import br.com.fiap.trabalhofinal.ui.cadastro.CadastroActivity
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.content_activity_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class ProductListActivity : AppCompatActivity(), OnClickProdutoItemListener {

    private val formRequestCode = 1

    val productViewModel: ProductViewModel by viewModel()

    val deleteViewModel: DeleteProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        productViewModel.getProducts()
        productViewModel.allProducts.observe(this, Observer {
            recyclerview.adapter = ProductListAdapter(it, this@ProductListActivity)
            recyclerview.layoutManager = LinearLayoutManager(this@ProductListActivity)
        })

        productViewModel.messageResponse.observe(this, Observer {
            if (it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        deleteViewModel.messageResponse.observe(this, Observer {
            if (it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        btCadastrar.setOnClickListener {
            startActivityForResult(Intent(this, CadastroActivity::class.java), formRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == formRequestCode && resultCode == Activity.RESULT_OK) {
            productViewModel.getProducts()
        }
    }

    override fun onDeleteClicked(produto: Produto) {
        deleteViewModel.delete(produto)
        deleteViewModel.isLoading.observe(this, Observer {
            if(!it) {
                productViewModel.getProducts()
            }
        })
    }

    override fun onEditClicked(produto: Produto) {
        val intent = Intent(this, CadastroActivity::class.java)
        intent.putExtra("PRODUTO", produto)
        startActivityForResult(intent, formRequestCode)
    }
}
