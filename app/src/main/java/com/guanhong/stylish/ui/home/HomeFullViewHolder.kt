package com.guanhong.stylish.ui.home

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.guanhong.stylish.R
import com.guanhong.stylish.model.Product

class HomeFullViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var context: Context
    private lateinit var listener: HomeFullViewHolderListener

    private val rootView = itemView.findViewById<ConstraintLayout>(R.id.rootView)
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val subTitle = itemView.findViewById<TextView>(R.id.subTitle)
    interface HomeFullViewHolderListener{
        fun itemClick(product: Product)
    }
    fun setResource(context: Context, listener: HomeFullViewHolderListener): RecyclerView.ViewHolder {

        this.context = context
        this.listener = listener
        return this
    }

    fun setResult(product: Product) {

        title.text = product.title
        subTitle.text = product.description
        Glide.with(context)
                .load(product.main_image)
                .centerCrop()
                .into(image)
        rootView.setOnClickListener {
            listener.itemClick(product)
        }
    }
}