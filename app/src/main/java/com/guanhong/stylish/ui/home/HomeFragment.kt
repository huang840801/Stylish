package com.guanhong.stylish.ui.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.R
import com.guanhong.stylish.di.DaggerHomeFragmentComponent
import com.guanhong.stylish.di.HomeFragmentPresenterModule
import javax.inject.Inject

class HomeFragment : Fragment(), HomeFragmentContract.View{

    @Inject
    lateinit var presenter: HomeFragmentPresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    fun newInstance() : HomeFragment{
        return HomeFragment()
    }
}