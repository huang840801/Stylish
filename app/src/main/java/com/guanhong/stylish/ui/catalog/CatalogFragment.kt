package com.guanhong.stylish.ui.catalog

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.R
import com.guanhong.stylish.ui.catalog.child.ViewPagerAdapter
import com.guanhong.stylish.ui.catalog.child.accessories.AccessoriesFragment
import com.guanhong.stylish.ui.catalog.child.men.MenFragment
import com.guanhong.stylish.ui.catalog.child.women.WomenFragment
import kotlinx.android.synthetic.main.fragment_catalog.*

class CatalogFragment : Fragment() {


    private var fragmentList = mutableListOf<Fragment>()
    private lateinit var menFragment: MenFragment
    private lateinit var womenFragment: WomenFragment
    private lateinit var accessoriesFragment: AccessoriesFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewPager()
        tabLayout.setupWithViewPager(viewPager)
    }

    fun newInstance(): CatalogFragment {
        return CatalogFragment()
    }

    private fun setViewPager() {

        menFragment = MenFragment().newInstance()
        womenFragment = WomenFragment().newInstance()
        accessoriesFragment = AccessoriesFragment().newInstance()

        fragmentList.add(menFragment)
        fragmentList.add(womenFragment)
        fragmentList.add(accessoriesFragment)

        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, fragmentList)

        viewPager.offscreenPageLimit = 3
        viewPager.adapter = viewPagerAdapter
    }
}