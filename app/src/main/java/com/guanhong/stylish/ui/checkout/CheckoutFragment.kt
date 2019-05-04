package com.guanhong.stylish.ui.checkout

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R
import com.guanhong.stylish.model.CartProduct
import kotlinx.android.synthetic.main.fragment_cart.*

class CheckoutFragment : BaseFragment() {

    private lateinit var listener: CheckoutFragmentListener
    private lateinit var adapter : CheckoutAdapter

    private var cartProductCount = 0
    private var cartProductList: MutableList<CartProduct> = mutableListOf()

    interface CheckoutFragmentListener {
        fun checkoutFragmentCreate()
        fun checkoutFragmentDestroy()
    }

    companion object {
        const val PRODUCT_COUNT = "product_count"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener.checkoutFragmentCreate()

        cartProductList.clear()

        if (arguments != null) {
            cartProductCount = arguments!!.getInt(PRODUCT_COUNT)

            for (i in 0 until cartProductCount) {

                val cartProduct = arguments!!.getSerializable("product" + i) as CartProduct
                cartProductList.add(cartProduct)
            }
        }

        adapter = CheckoutAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        adapter.bindCartProductList(cartProductList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listener.checkoutFragmentDestroy()
    }

    fun newInstance(): CheckoutFragment {
        return CheckoutFragment()
    }

    fun setListener(listener: CheckoutFragmentListener) {
        this.listener = listener
    }
}