package com.guanhong.stylish.di.catalog

import android.support.v4.app.Fragment
import com.guanhong.stylish.ui.catalog.CatalogFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class CatalogFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(CatalogFragment::class)
    abstract fun bind(builder: CatalogSubComponent.Builder): AndroidInjector.Factory<out Fragment>
}