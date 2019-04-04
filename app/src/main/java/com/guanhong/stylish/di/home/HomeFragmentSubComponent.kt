package com.guanhong.stylish.di.home

import android.support.v4.app.Fragment
import com.guanhong.stylish.di.home.HomeFragmentPresenterModule
import com.guanhong.stylish.ui.home.HomeFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [HomeFragmentPresenterModule::class])
interface HomeFragmentSubComponent: AndroidInjector<HomeFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<HomeFragment>()
}