package com.guanhong.stylish.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guanhong.stylish.R
import com.guanhong.stylish.model.Product

class HomeAdapter(private val listener: HomeAdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
        HomeFullViewHolder.HomeFullViewHolderListener,
        HomeNormalHolder.HomeNormalHolderListener {

    private var hotList: List<Any> = listOf()

    companion object {
        private const val TITLE_TYPE = 1
        private const val FULL_VIEW_TYPE = 2
        private const val NORMAL_TYPE = 3
    }

    interface HomeAdapterListener {
        fun itemClick(product: Product)
    }

    override fun getItemCount(): Int = hotList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TITLE_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_title, parent, false)
                HomeTitleHolder(view).setResource()
            }
            FULL_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_full_view, parent, false)
                HomeFullViewHolder(view).setResource(parent.context, this)
            }
            NORMAL_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_normal, parent, false)
                HomeNormalHolder(view).setResource(parent.context, this)
            }
            else -> {
                throw Exception("ViewType not match")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is HomeTitleHolder -> {
                holder.setResult(hotList[position] as String)
            }
            is HomeFullViewHolder -> {
                holder.setResult(hotList[position] as Product)
            }
            is HomeNormalHolder -> {
                holder.setResult(hotList[position] as Product)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when {
            (hotList[position] is String) -> TITLE_TYPE
            (hotList[position] is Product && position == 1) -> FULL_VIEW_TYPE
            else -> NORMAL_TYPE
        }
    }

    override fun itemClick(product: Product) {
        listener.itemClick(product)
    }

    fun onBindMarketingHots(hotList: ArrayList<Any>) {
        this.hotList = hotList
        notifyDataSetChanged()
    }
}