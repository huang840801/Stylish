package com.guanhong.stylish.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guanhong.stylish.R

class DetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var imagesList: List<String> = listOf()

    companion object {
        const val IMAGE_TYPE = 1
    }

    override fun getItemCount(): Int {
        return imagesList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            IMAGE_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_gallery_photo, parent, false)
                return DetailPhotoHolder(view).setResource(parent.context)
            }
            else -> {
                throw Exception("ViewType not match")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder) {
            is DetailPhotoHolder ->{
                holder.setResult(imagesList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return IMAGE_TYPE
    }

    fun onBindImageList(imagesList: List<String>) {
        this.imagesList = imagesList
        notifyDataSetChanged()
    }
}