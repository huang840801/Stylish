package com.guanhong.stylish.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guanhong.stylish.R
import com.guanhong.stylish.`object`.Product

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var productList: List<Product> = listOf()

    companion object {
        const val MAIN_TYPE = 1
        const val SECONDARY_TYPE = 2
    }
    

    override fun getItemCount(): Int {
        return productList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            MAIN_TYPE ->{
                HomeMainHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_main, parent))
            }
            SECONDARY_TYPE->{
                HomeSecondaryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_secondary, parent))
            }
            else ->{
                throw Exception("ViewType not match")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeMainHolder ->{ holder.setResult(productList[position])}
            is HomeSecondaryHolder ->{holder.setResult(productList[position])}
        }
    }

    override fun getItemViewType(position: Int): Int {

        return if (position == 0) {
            MAIN_TYPE
        } else{
            SECONDARY_TYPE
        }
    }

    fun onBindMarketingHots(productList: List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }
}