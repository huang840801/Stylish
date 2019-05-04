package com.guanhong.stylish.ui.cart

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.guanhong.stylish.R
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.sql.SqlDbHelper
import com.guanhong.stylish.util.setEdge

class CartProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var context: Context
    private lateinit var listener: CartProductHolderListener

    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val remove = itemView.findViewById<TextView>(R.id.remove)
    private val color = itemView.findViewById<View>(R.id.color)
    private val size = itemView.findViewById<TextView>(R.id.size)
    private val price = itemView.findViewById<TextView>(R.id.price)
    private val count = itemView.findViewById<TextView>(R.id.count)
    private val subtract = itemView.findViewById<ImageView>(R.id.subtractButton)
    private val add = itemView.findViewById<ImageView>(R.id.addButton)

    interface CartProductHolderListener {
        fun removeClick(productId: String)
    }

    fun setResource(context: Context, listener: CartProductHolderListener): RecyclerView.ViewHolder {
        this.context = context
        this.listener = listener
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
        count.text = cartProduct.selectedStock.toString()

        color.setBackgroundColor(Color.parseColor("#" + cartProduct.selectedColorCode))
        color.setEdge(
                R.dimen.edge_add2cart_select_1dp,
                context.resources.getColor(R.color.black)
        )
        add.setEdge(
                R.dimen.edge_add2cart_select_2dp,
                context.resources.getColor(R.color.black)
        )
        count.setEdge(
                R.dimen.edge_add2cart_select_2dp,
                context.resources.getColor(R.color.black)
        )
        subtract.setEdge(
                R.dimen.edge_add2cart_select_2dp,
                context.resources.getColor(R.color.black)
        )

        add.setOnClickListener {
            if (count.text.toString().toInt() < cartProduct.stock) {

                count.text = (count.text.toString().toInt() + 1).toString()
            }
        }
        subtract.setOnClickListener {
            if (count.text.toString().toInt() > 1) {

                count.text = (count.text.toString().toInt() - 1).toString()
            }
        }
        remove.setOnClickListener {
            listener.removeClick(cartProduct.id)
        }

    }
}