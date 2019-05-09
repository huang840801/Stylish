package com.guanhong.stylish.ui.checkout

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.model.OrderCheckout
import com.guanhong.stylish.model.OrderProduct
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject

class CheckoutFragment : BaseFragment(),
        CheckoutContract.View,
        CheckoutAdapter.CheckoutAdapterListener {

    @Inject
    lateinit var presenter: CheckoutPresenter

    private lateinit var listener: CheckoutFragmentListener
    private lateinit var adapter: CheckoutAdapter

    private var cartProductCount = 0
    private var cartProductList: MutableList<CartProduct> = mutableListOf()

    interface CheckoutFragmentListener {
        fun checkoutFragmentCreate()
        fun checkoutFragmentDestroy()
    }

    companion object {
        const val PRODUCT_COUNT = "product_count"
    }

    override fun onAttach(activity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
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

        adapter = CheckoutAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        adapter.bindCartProductList(cartProductList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listener.checkoutFragmentDestroy()
    }

    override fun orderCheckout(orderCheckout: OrderCheckout) {

        val productList = mutableListOf<OrderProduct>()

        cartProductList.forEach {

            val orderProduct = OrderProduct().apply {
                id = it.id
                name = it.title
                price = it.price
                orderColor.code = it.colorCode
                orderColor.name = it.colorName
                size = it.selectedSize
                qty = it.selectedStock
            }

            productList.add(orderProduct)
        }

        orderCheckout.productList = productList

        presenter.orderCheckout(orderCheckout)
    }

    fun newInstance(): CheckoutFragment {
        return CheckoutFragment()
    }

    fun setListener(listener: CheckoutFragmentListener) {
        this.listener = listener
    }
}