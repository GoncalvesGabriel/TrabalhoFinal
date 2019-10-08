package br.com.fiap.trabalhofinal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.listener.OnClickProdutoItemListener
import br.com.fiap.trabalhofinal.model.Produto

class ProductListAdapter internal constructor(
    context: Context,
    private val itemClickListener: OnClickProdutoItemListener
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var products = emptyList<Produto>()

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productItemView: TextView =  itemView.findViewById(R.id.textView)
        val editButton: ImageButton =  itemView.findViewById(R.id.btEdit)
        val deleteButton: ImageButton =  itemView.findViewById(R.id.btDelete)

        fun bind(produto: Produto, clickListener: OnClickProdutoItemListener) {
            itemView.setOnClickListener {
                clickListener.onItemClicked(produto)
            }
            deleteButton.setOnClickListener {
                clickListener.onDeleteClicked(produto)
            }
            editButton.setOnClickListener {
                clickListener.onEditClicked(produto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = products[position]
        holder.productItemView.text = current.nome
        holder.bind(current, this.itemClickListener)
    }

    internal fun setProduct(products: List<Produto>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun getItemCount() = products.size

}