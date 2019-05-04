package com.guanhong.stylish.ui.cart

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guanhong.stylish.R
import com.guanhong.stylish.model.CartProduct

class CartAdapter(private val listener: CartAdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
        CartProductHolder.CartProductHolderListener {

    private var cartProducts: List<CartProduct> = listOf()

    companion object {
        const val NORMAL_TYPE = 1
    }

    interface CartAdapterListener{
        fun removeClick(productId: String)
    }
    override fun getItemCount(): Int = cartProducts.count()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            NORMAL_TYPE ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart_product, parent, false)
                return CartProductHolder(view).setResource(parent.context, this)
            }
            else->{
                throw Exception("ViewType not match")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is CartProductHolder ->{
                holder.setResult(cartProducts[position])
            }
        }
    }

    override fun removeClick(productId: String) {
        listener.removeClick(productId)
    }

    override fun getItemViewType(position: Int): Int {
        return NORMAL_TYPE
    }

    fun onBindCartList(cartProducts: List<CartProduct>) {
        this.cartProducts = cartProducts
        notifyDataSetChanged()
    }
}