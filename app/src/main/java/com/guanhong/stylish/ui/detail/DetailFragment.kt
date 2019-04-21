package com.guanhong.stylish.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R
import com.guanhong.stylish.model.Product
import kotlinx.android.synthetic.main.fragment_detail.*
import android.widget.LinearLayout
import android.graphics.drawable.GradientDrawable
import kotlinx.android.synthetic.main.item_detail_color.view.*


class DetailFragment : BaseFragment() {

    private lateinit var listener: DetailFragmentListener

    private lateinit var product: Product
    private lateinit var adapter: DetailAdapter

    interface DetailFragmentListener {
        fun detailFragmentCreate()
        fun detailFragmentDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener.detailFragmentCreate()

        if (arguments != null) {
            product = arguments!!.getSerializable("product") as Product
        }

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = DetailAdapter()
        recyclerView.adapter = adapter
        LinearSnapHelper().attachToRecyclerView(recyclerView)

        adapter.onBindImageList(product.images)

        title.text = product.title
        price.text = "NT$ " + product.price.toString()
        productId.text = product.id
        content.text = product.story

        size.text = if (product.sizes.count() == 1) {
            product.sizes.first()
        } else {
            product.sizes.first() + " - " + product.sizes.last()
        }
        stock.text = product.variants.sumBy { it.stock }.toString()
        texture.text = product.texture
        wash.text = product.wash
        place.text = product.place
        note.text = product.note

        colorLayout.removeAllViews()
        product.colors.forEach {

            val itemView = View.inflate(context, R.layout.item_detail_color, null)

            val gd = GradientDrawable(
                    GradientDrawable.Orientation.BOTTOM_TOP,
                    intArrayOf(Color.parseColor("#" + it.code),Color.parseColor("#" + it.code)))
            gd.setStroke(1, Color.BLACK)
            itemView.color.setBackgroundDrawable(gd)
            colorLayout.addView(itemView)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("Huang", " onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Huang", " onDestroyView")
        listener.detailFragmentDestroy()
    }

    fun newInstance(): DetailFragment {
        return DetailFragment()
    }

    fun setListener(listener: DetailFragmentListener) {
        this.listener = listener
    }
}