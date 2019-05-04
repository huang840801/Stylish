package com.guanhong.stylish.ui.checkout

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.guanhong.stylish.R
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.util.setEdge

class CartProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var context: Context

    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val color = itemView.findViewById<View>(R.id.color)
    private val size = itemView.findViewById<TextView>(R.id.size)
    private val price = itemView.findViewById<TextView>(R.id.price)
    private val count = itemView.findViewById<TextView>(R.id.count)
    fun setResource(context: Context): RecyclerView.ViewHolder {

        this.context = context
        return this
    }

    fun setResult(cartProduct: CartProduct) {
        Glide.with(context)
                .load(cartProduct.mainImage)
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(image)

        title.text = cartProduct.title
        size.text = cartProduct.selectedSize
        price.text = "NT $ " + cartProduct.price.toString()
        count.text = "x " + cartProduct.selectedStock.toString()

        color.setBackgroundColor(Color.parseColor("#" + cartProduct.selectedColorCode))
        color.setEdge(
                R.dimen.edge_add2cart_select_1dp,
                context.resources.getColor(R.color.black)
        )
    }
}