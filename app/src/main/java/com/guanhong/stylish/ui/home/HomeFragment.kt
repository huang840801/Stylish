package com.guanhong.stylish.ui.home

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.util.hide
import com.guanhong.stylish.util.show
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeContract.View, HomeAdapter.HomeAdapterListener {

    @Inject
    lateinit var presenter: HomePresenter

    private lateinit var adapter: HomeAdapter
    private lateinit var listener: HomeFragmentListener

    interface HomeFragmentListener{
        fun itemClick(product: Product)
    }
    override fun onAttach(activity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = this.adapter

        progressBar.show()
        presenter.getMarketingHots()
    }

    override fun onBindMarketingHots(hotList: ArrayList<Any>) {

       activity!!.runOnUiThread {
           adapter.onBindMarketingHots(hotList)
           progressBar.hide()
       }
    }
    override fun itemClick(product: Product) {
        listener.itemClick(product)
    }

    fun newInstance(): HomeFragment {
        return HomeFragment()
    }

    fun setListener(listener: HomeFragmentListener) {
        this.listener = listener
    }
}