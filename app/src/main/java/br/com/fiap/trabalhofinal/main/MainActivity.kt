package br.com.fiap.trabalhofinal.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

class MainActivity : AppCompatActivity(), OnClickProdutoItemListener {

    lateinit var adapter : ProductListAdapter

    val productViewModel: ProductViewModel by viewModel()

    val newProdutoRequestCode = 2

    val editProdutoRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.adapter = ProductListAdapter(this@MainActivity, this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        productViewModel.allProduct.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setProduct(it) }
        })
        btCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivityForResult(intent, newProdutoRequestCode)
        }
    }

    override fun onItemClicked(produto: Produto) {
        val intent = Intent(this, CadastroActivity::class.java)
        intent.putExtra("PRODUTO", produto)
        startActivityForResult(intent, editProdutoRequestCode)
    }
    
}


