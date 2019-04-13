package com.guanhong.stylish.ui.home

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guanhong.stylish.R
import com.guanhong.stylish.`object`.Product
class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var hotList: List<Any> = listOf()

    companion object {
        const val TITLE_TYPE = 1
        const val FULL_VIEW_TYPE = 2
        const val NORMAL_TYPE = 3
    }

    override fun getItemCount(): Int = hotList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            TITLE_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_title, parent, false)
                return HomeTitleHolder(view).setResource()
            }
            FULL_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_full_view, parent, false)
                return HomeFullViewHolder(view).setResource(parent.context)
            }
            NORMAL_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_normal, parent, false)
                return HomeNormalHolder(view).setResource(parent.context)
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

    fun onBindMarketingHots(hotList: ArrayList<Any>) {
        this.hotList = hotList
        notifyDataSetChanged()
    }
}