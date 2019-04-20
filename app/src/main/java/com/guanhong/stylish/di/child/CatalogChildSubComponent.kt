package com.guanhong.stylish.di.child

import com.guanhong.stylish.ui.catalog.child.CatalogChildFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [CatalogChildPresenterModule::class])
interface CatalogChildSubComponent : AndroidInjector<CatalogChildFragment>{
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<CatalogChildFragment>()
}