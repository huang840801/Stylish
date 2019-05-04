package com.guanhong.stylish.ui.checkout

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R

class CheckoutFragment : BaseFragment() {

    private lateinit var listener: CheckoutFragmentListener

    interface CheckoutFragmentListener{
        fun checkoutFragmentCreate()
        fun checkoutFragmentDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Huang", " checkout onViewCreated")

        listener.checkoutFragmentCreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d("Huang", " checkout onDestroyView")
        listener.checkoutFragmentDestroy()
    }

    fun newInstance(): CheckoutFragment {
        return CheckoutFragment()
    }

    fun setListener(listener: CheckoutFragmentListener) {
        this.listener = listener
    }
}