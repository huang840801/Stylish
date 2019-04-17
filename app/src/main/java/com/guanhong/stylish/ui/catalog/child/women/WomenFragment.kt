package com.guanhong.stylish.ui.catalog.child.women

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R

class WomenFragment : BaseFragment(), WomenContract.View{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_women, container, false)
    }
    fun newInstance() : WomenFragment{
        return WomenFragment()
    }
}