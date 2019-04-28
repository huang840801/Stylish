package com.guanhong.stylish.ui.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.guanhong.stylish.R

class HomeTitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title)

    fun setResource(): RecyclerView.ViewHolder {
        return this
    }

    fun setResult(title: String) {
        this.title.text = title
    }
}