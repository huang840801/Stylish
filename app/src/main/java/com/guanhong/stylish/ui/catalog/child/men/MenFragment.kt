package com.guanhong.stylish.ui.catalog.child.men

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R

class MenFragment : BaseFragment(), MenContract.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_men, container, false)
    }

    fun newInstance(): MenFragment{
        return MenFragment()
    }
}