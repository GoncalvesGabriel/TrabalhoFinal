package br.com.fiap.trabalhofinal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.model.Produto

class ProductListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var products = emptyList<Produto>()

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val productItemView: TextView =  itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = products[position]
        holder.productItemView.text = current.nome;
    }

    internal fun setProduct(products: List<Produto>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun getItemCount() = products.size

}