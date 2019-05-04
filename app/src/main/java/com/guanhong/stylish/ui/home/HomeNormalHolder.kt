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
import tech.cherri.tpdirect.api.TPDSetup

class HomeNormalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var context: Context
    private lateinit var listener: HomeNormalHolderListener

    private val rootView = itemView.findViewById<ConstraintLayout>(R.id.rootView)
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val subTitle = itemView.findViewById<TextView>(R.id.subTitle)
    private val leftImage = itemView.findViewById<ImageView>(R.id.leftImage)
    private val topImage = itemView.findViewById<ImageView>(R.id.topImage)
    private val rightImage = itemView.findViewById<ImageView>(R.id.rightImage)
    private val bottomImage = itemView.findViewById<ImageView>(R.id.bottomImage)

    interface HomeNormalHolderListener{
        fun itemClick(product: Product)
    }
    fun setResource(context: Context, listener: HomeNormalHolderListener): RecyclerView.ViewHolder {

        this.context = context
        this.listener = listener
        return this
    }

    fun setResult(product: Product) {

        title.text = product.title
        subTitle.text = product.description

        setImage(leftImage, product.images[0])
        setImage(topImage, product.images[1])
        setImage(bottomImage, product.images[2])
        setImage(rightImage, product.images[3])

        rootView.setOnClickListener {
            listener.itemClick(product)
        }
    }

    private fun setImage(image: ImageView, url: String) {
        Glide.with(context)
                .load(url)
                .into(image)
    }
}