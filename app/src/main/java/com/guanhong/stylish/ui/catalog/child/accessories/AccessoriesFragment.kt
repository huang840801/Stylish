package com.guanhong.stylish.ui.catalog.child.accessories
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R

class AccessoriesFragment : BaseFragment(), AccessoriesContract.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_accessories, container, false)
    }

    fun newInstance() : AccessoriesFragment{
        return  AccessoriesFragment()
    }
}