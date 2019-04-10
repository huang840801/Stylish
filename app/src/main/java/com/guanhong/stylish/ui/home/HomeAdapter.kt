package com.guanhong.stylish.ui.home

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guanhong.stylish.R
import com.guanhong.stylish.`object`.Hots

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var hotsList: List<Hots> = listOf()
    private val titlePositionList = ArrayList<Int>()
    private var totalItemCount = 0

    companion object {
        const val TITLE_TYPE = 1
        const val FULL_VIEW_TYPE = 2
        const val NORMAL_TYPE = 3
    }

    override fun getItemCount(): Int {
        Log.d("Huang", "   productList.count() = " + totalItemCount)
        return totalItemCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        titlePositionList.add(0)
        hotsList.forEach {
            titlePositionList.add(it.products.count() + titlePositionList.last())
        }

        titlePositionList.forEach {
            Log.d("Huang", "  deded" + it)
        }

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

        var thisPositionIndex = 0

        titlePositionList.forEach {

            if (it > thisPositionIndex) {
                thisPositionIndex = titlePositionList.indexOf(it)
                return@forEach
            }
        }

        Log.d("Huang", " index = " + thisPositionIndex)
        when (holder) {
            is HomeFullViewHolder -> {
                holder.setResult(hotsList[thisPositionIndex].title)
            }
            is HomeNormalHolder -> {
                holder.setResult(hotsList[thisPositionIndex].products[0])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        if (position == 1) {
            return FULL_VIEW_TYPE
        }
        return if (titlePositionList.any { it == position }) {
            TITLE_TYPE
        } else {
            NORMAL_TYPE
        }
    }

    fun onBindMarketingHots(hotsList: List<Hots>) {
        this.hotsList = hotsList

        hotsList.forEach {
            totalItemCount += it.products.count()
        }
        notifyDataSetChanged()
    }
}