package com.guanhong.stylish.di.catalog

import com.guanhong.stylish.ui.catalog.CatalogFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [CatalogPresenterModule::class])
interface CatalogSubComponent :AndroidInjector<CatalogFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<CatalogFragment>()
}