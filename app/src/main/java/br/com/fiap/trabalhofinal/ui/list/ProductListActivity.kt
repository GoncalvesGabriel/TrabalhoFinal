package br.com.fiap.trabalhofinal.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.adapter.ProductListAdapter
import br.com.fiap.trabalhofinal.listener.OnClickProdutoItemListener
import br.com.fiap.trabalhofinal.model.Produto
import br.com.fiap.trabalhofinal.model.view.ProductViewModel
import br.com.fiap.trabalhofinal.ui.cadastro.CadastroActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class ProductListActivity : AppCompatActivity(), OnClickProdutoItemListener {

        lateinit var adapter : ProductListAdapter

        val productViewModel: ProductViewModel by viewModel()

        val newProdutoRequestCode = 2

        val editProdutoRequestCode = 1

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            this.adapter = ProductListAdapter(this@ProductListActivity, this)
            setContentView(R.layout.activity_product_list)
            setSupportActionBar(toolbar)
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)

            productViewModel.allProduct.observe(this, Observer { products ->
                // Update the cached copy of the products in the adapter.
                products?.let { adapter.setProduct(it) }
            })
            btCadastrar.setOnClickListener {
                startActivityForResult(Intent(this, CadastroActivity::class.java), newProdutoRequestCode)
            }
        }

        override fun onItemClicked(produto: Produto) {

        }

        override fun onDeleteClicked(produto: Produto) {
            productViewModel.delete(produto)
        }

        override fun onEditClicked(produto: Produto) {
            val intent = Intent(this, CadastroActivity::class.java)
            intent.putExtra("PRODUTO", produto)
            startActivityForResult(intent, editProdutoRequestCode)
        }
}
