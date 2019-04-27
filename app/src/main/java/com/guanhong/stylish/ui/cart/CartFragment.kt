package com.guanhong.stylish.ui.cart

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.util.hide
import com.guanhong.stylish.util.show
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject

class CartFragment :BaseFragment(), CartContract.View {

    @Inject
    lateinit var presenter : CartPresenter

    private lateinit var adapter : CartAdapter

    override fun onAttach(activity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    fun newInstance() : CartFragment{
        return CartFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar.show()

        adapter = CartAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        getCartProductList()
    }

    override fun showCartProductList(cartProducts: List<CartProduct>) {

        adapter.onBindCartList(cartProducts)
        progressBar.hide()
    }

    private fun getCartProductList(){
        presenter.getCartProductList(context!!)
    }
}