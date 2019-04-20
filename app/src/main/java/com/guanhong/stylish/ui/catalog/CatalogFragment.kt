package com.guanhong.stylish.ui.catalog

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.ui.catalog.child.ViewPagerAdapter
import com.guanhong.stylish.ui.catalog.child.CatalogChildFragment
import com.guanhong.stylish.ui.catalog.child.TYPE
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_catalog.*
import javax.inject.Inject

class CatalogFragment : BaseFragment(), CatalogContract.View, CatalogChildFragment.CatalogChildFragmentListener {

    @Inject
    lateinit var presenter: CatalogPresenter

    private lateinit var listener: CatalogFragmentListener
    private var fragmentList = mutableListOf<Fragment>()

    interface CatalogFragmentListener {
        fun itemClick(product: Product)
    }

    override fun onAttach(activity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewPager()
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun itemClick(product: Product) {
        listener.itemClick(product)
    }

    fun newInstance(): CatalogFragment {
        return CatalogFragment()
    }

    private fun setViewPager() {

        fragmentList.add(createChildFragment(TYPE.WOMEN))
        fragmentList.add(createChildFragment(TYPE.MEN))
        fragmentList.add(createChildFragment(TYPE.ACCESSORIES))
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, fragmentList)

        viewPager.offscreenPageLimit = 3
        viewPager.adapter = viewPagerAdapter
    }

    private fun createChildFragment(itemType: TYPE): CatalogChildFragment {

        val fragment = CatalogChildFragment().newInstance()
        fragment.setListener(this)
        fragment.itemType = itemType

        return fragment
    }

    fun setListener(listener: CatalogFragmentListener) {
        this.listener = listener
    }
}