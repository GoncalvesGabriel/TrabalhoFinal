package br.com.fiap.trabalhofinal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.listener.OnClickProdutoItemListener
import br.com.fiap.trabalhofinal.model.Produto

class ProductListAdapter (
    val products: List<Produto>,
    val itemClickListener: OnClickProdutoItemListener
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productItemView: TextView =  itemView.findViewById(R.id.textView)
        val editButton: ImageButton =  itemView.findViewById(R.id.btEdit)
        val deleteButton: ImageButton =  itemView.findViewById(R.id.btDelete)

        fun bind(produto: Produto, clickListener: OnClickProdutoItemListener) {
            this.productItemView.text = produto.nome
            deleteButton.setOnClickListener {
                clickListener.onDeleteClicked(produto)
            }
            editButton.setOnClickListener {
                clickListener.onEditClicked(produto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = products[position]
        holder.bind(current, this.itemClickListener)
    }

    override fun getItemCount() = products.size

}