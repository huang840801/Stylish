package com.guanhong.stylish.di.child

import android.support.v4.app.Fragment
import com.guanhong.stylish.ui.catalog.child.CatalogChildFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class CatalogChildFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(CatalogChildFragment::class)
    abstract fun bind(builder: CatalogChildSubComponent.Builder): AndroidInjector.Factory<out Fragment>
}