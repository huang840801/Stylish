package com.guanhong.stylish.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R
import com.guanhong.stylish.model.Product
import kotlinx.android.synthetic.main.fragment_detail.*
import android.graphics.drawable.GradientDrawable
import com.guanhong.stylish.ui.add2cart.AddCartFragment
import com.guanhong.stylish.util.hide
import com.guanhong.stylish.util.show
import kotlinx.android.synthetic.main.item_detail_color.view.*


class DetailFragment : BaseFragment(), AddCartFragment.AddCartFragmentListener {

    private lateinit var listener: DetailFragmentListener

    private lateinit var product: Product
    private lateinit var adapter: DetailAdapter
    private lateinit var addToCartFragment: AddCartFragment

    interface DetailFragmentListener {
        fun detailFragmentCreate()
        fun detailFragmentDestroy()
        fun addToCart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener.detailFragmentCreate()

        addToCartFragment = AddCartFragment()
        addToCartFragment.setListener(this)
        if (arguments != null) {
            product = arguments!!.getSerializable("product") as Product
        }

        setRecyclerView(product)
        setProductDetail(product)

        addToCart.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("product", product)
            addToCartFragment.arguments = bundle

            addToCartFragment.show(activity!!.supportFragmentManager, "add2Cart")
        }
    }

    override fun addToCart(isSuccess: Boolean) {
        if (isSuccess) {
            resultLayout.show()
            resultImage.setImageResource(R.drawable.icons_44px_success01)
            resultText.text = "加入成功"
            listener.addToCart()
        } else {
            resultLayout.show()
            resultImage.setImageResource(R.drawable.icons_44px_failed)
            resultText.text = "加入失敗"
        }
        resultLayout.setOnClickListener {
            resultLayout.hide()
        }
    }

    private fun setRecyclerView(product: Product) {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = DetailAdapter()
        recyclerView.adapter = adapter
        LinearSnapHelper().attachToRecyclerView(recyclerView)

        adapter.onBindImageList(product.images)
    }

    private fun setProductDetail(product: Product) {
        title.text = product.title
        price.text = "NT$ " + product.price.toString()
        productId.text = product.id
        content.text = product.story

        size.text = if (product.sizes.count() == 1) {
            product.sizes.first()
        } else {
            product.sizes.first() + " - " + product.sizes.last()
        }
        stockCount.text = product.variants.sumBy { it.stock }.toString()
        texture.text = product.texture
        wash.text = product.wash
        place.text = product.place
        note.text = product.note

        colorLayout.removeAllViews()
        product.colors.forEach {

            val itemView = View.inflate(context, R.layout.item_detail_color, null)

            val gd = GradientDrawable(
                    GradientDrawable.Orientation.BOTTOM_TOP,
                    intArrayOf(Color.parseColor("#" + it.code), Color.parseColor("#" + it.code)))
            gd.setStroke(1, Color.BLACK)

            itemView.color.setBackgroundDrawable(gd)
            colorLayout.addView(itemView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listener.detailFragmentDestroy()
    }

    fun newInstance(): DetailFragment {
        return DetailFragment()
    }

    fun setListener(listener: DetailFragmentListener) {
        this.listener = listener
    }
}