package com.guanhong.stylish.ui.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.R
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import okhttp3.*
import javax.inject.Inject

class HomeFragment : Fragment(), HomeFragmentContract.View {

    @Inject
    lateinit var presenter: HomeFragmentPresenter

    override fun onAttach(activity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getMarketingHots()
    }

    fun newInstance(): HomeFragment {
        return HomeFragment()
    }
}