package com.guanhong.stylish.di.add2cart

import android.support.v4.app.Fragment
import com.guanhong.stylish.ui.add2cart.AddCartFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class AddCartFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(AddCartFragment::class)
    abstract fun bind(builder: AddCartSubComponent.Builder): AndroidInjector.Factory<out Fragment>
}