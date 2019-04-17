package com.guanhong.stylish.ui.catalog.child

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.guanhong.stylish.R
import com.guanhong.stylish.Stylish.Companion.context

class ViewPagerAdapter(fragmentManager: FragmentManager, private val fragmentList: List<Fragment>)
    : FragmentPagerAdapter(fragmentManager) {

    private val tabTitles = listOf(
            context.getString(R.string.women),
            context.getString(R.string.men),
            context.getString(R.string.accessories))

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}