package com.guanhong.stylish.di.home

import android.support.v4.app.Fragment
import com.guanhong.stylish.ui.home.HomeFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class HomeFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    abstract fun bind(builder: HomeSubComponent.Builder): AndroidInjector.Factory<out Fragment>
}