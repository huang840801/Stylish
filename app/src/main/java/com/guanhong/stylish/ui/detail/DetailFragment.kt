package com.guanhong.stylish.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R

class DetailFragment : BaseFragment() {

    private lateinit var listener: DetailFragmentListener

    interface DetailFragmentListener {
        fun detailFragmentCreate()
        fun detailFragmentDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener.detailFragmentCreate()
        if (arguments != null) {
            val data = arguments!!.getString("product")
            Log.d("Huang", " data = " + arguments!!.getSerializable("product"))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        listener.detailFragmentDestroy()
    }

    fun newInstance(): DetailFragment {
        return DetailFragment()
    }

    fun setListener(listener: DetailFragmentListener) {
        this.listener = listener
    }
}