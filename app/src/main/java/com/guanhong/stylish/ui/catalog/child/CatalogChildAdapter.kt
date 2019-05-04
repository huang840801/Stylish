package com.guanhong.stylish.ui.catalog.child

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guanhong.stylish.R
import com.guanhong.stylish.model.Product

class CatalogChildAdapter(private val listener: CatalogChildAdapterListener) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(),
        CatalogChildHolder.CatalogChildHolderListener {

    private var productList: List<Product> = listOf()

    companion object {
      private  const val NORMAL_TYPE = 1
    }

    interface CatalogChildAdapterListener {
        fun itemClick(product: Product)
    }

    override fun getItemCount(): Int {
        return productList.count()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {

            NORMAL_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_catolog_child, parent, false)
                CatalogChildHolder(view).setResource(parent.context, this)
            }
            else -> {
                throw Exception("ViewType not match")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CatalogChildHolder -> {
                holder.setResult(productList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return NORMAL_TYPE
    }

    override fun itemClick(product: Product) {
        listener.itemClick(product)
    }

    fun setProductList(productList: List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }
}