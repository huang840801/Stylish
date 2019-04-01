package com.guanhong.stylish.di

import com.guanhong.stylish.ui.home.HomeFragment
import dagger.Component

@Component(modules = [HomeFragmentPresenterModule::class])
interface HomeFragmentComponent {
    fun inject(fragment:HomeFragment)
}