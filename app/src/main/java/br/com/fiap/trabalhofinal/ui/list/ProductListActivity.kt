package br.com.fiap.trabalhofinal.ui.list

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
            startActivity(
                Intent(this, CadastroActivity::class.java)
            )
        }
    }

    override fun onDeleteClicked(produto: Produto) {
        deleteViewModel.delete(produto)
        deleteViewModel.isLoading.observe(this, Observer {
            if(!it) {
                productViewModel.getProducts()
                productViewModel.isLoading.removeObservers(this)
            }
        })
    }

    override fun onEditClicked(produto: Produto) {
        val intent = Intent(this, CadastroActivity::class.java)
        intent.putExtra("PRODUTO", produto)
        startActivity(intent)
    }
}
