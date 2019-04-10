package com.guanhong.stylish.ui.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.guanhong.stylish.R

class HomeFullViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var context: Context
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val image = itemView.findViewById<ImageView>(R.id.image)

    fun setResource(context: Context): RecyclerView.ViewHolder {

        this.context = context
        return this
    }

    fun setResult(product: String) {

        title.text = product
        Log.d("Huang", "ggggg")
//        Glide.with(context)
//                .load(product.main_image)
//                .centerCrop()
//                .into(image)
    }
}