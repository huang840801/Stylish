package com.guanhong.stylish.ui.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.guanhong.stylish.R
import com.guanhong.stylish.model.Product

class HomeFullViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var context: Context
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val subTitle = itemView.findViewById<TextView>(R.id.subTitle)

    fun setResource(context: Context): RecyclerView.ViewHolder {

        this.context = context
        return this
    }

    fun setResult(product: Product) {

        title.text = product.title
        subTitle.text = product.description
        Glide.with(context)
                .load(product.main_image)
                .centerCrop()
                .into(image)
    }
}