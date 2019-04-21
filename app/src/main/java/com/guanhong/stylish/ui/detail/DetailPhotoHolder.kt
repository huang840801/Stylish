package com.guanhong.stylish.ui.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.guanhong.stylish.R

class DetailPhotoHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var context: Context

    private val image = itemView.findViewById<ImageView>(R.id.image)

    fun setResource(context: Context): RecyclerView.ViewHolder {
        this.context = context
        return this
    }

    fun setResult(url: String) {

        Glide.with(context).load(url).into(image)
    }
}