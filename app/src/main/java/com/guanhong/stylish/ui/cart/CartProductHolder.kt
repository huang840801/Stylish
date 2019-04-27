package com.guanhong.stylish.ui.cart

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.guanhong.stylish.model.CartProduct

class CartProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var context: Context
    private lateinit var listener: CartProductHolderListener

    interface CartProductHolderListener {

    }

    fun setResource(context: Context, listener: CartProductHolderListener): RecyclerView.ViewHolder {
        this.context = context
        this.listener = listener
        return this
    }
    fun setResult(cartProduct: CartProduct) {

    }
}