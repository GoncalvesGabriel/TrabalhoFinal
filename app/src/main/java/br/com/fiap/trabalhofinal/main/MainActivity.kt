package br.com.fiap.trabalhofinal.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.adapter = ProductListAdapter(this@MainActivity, this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        productViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setProduct(it) }
        })
        btCadastrar.setOnClickListener {
            startActivityForResult(Intent(this, CadastroActivity::class.java), newProdutoRequestCode)
        }
    }

    override fun onItemClicked(produto: Produto) {
        Toast.makeText(this@MainActivity, "Produto clicado: " + produto.nome, Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClicked(produto: Produto) {
        productViewModel.delete(produto)
    }

    override fun onEditClicked(produto: Produto) {
        TODO("OI GABES, POE O REDIRECT PRO CADASTRO AQUI")
    }
}


