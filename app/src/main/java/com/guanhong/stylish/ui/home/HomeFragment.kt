package com.guanhong.stylish.ui.home

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.R
import com.guanhong.stylish.`object`.Data
import com.guanhong.stylish.api.DataResourceCallback
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment(), HomeFragmentContract.View {

    @Inject
    lateinit var presenter: HomeFragmentPresenter

    private lateinit var adapter: HomeAdapter

    override fun onAttach(activity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = this.adapter

        presenter.getMarketingHots()
    }

    override fun onBindMarketingHots(hotsResponse: Data) {
        title.text = hotsResponse.data[0].title
        adapter.onBindMarketingHots(hotsResponse.data[0].products)
    }

    fun newInstance(): HomeFragment {
        return HomeFragment()
    }
}