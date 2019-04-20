package com.guanhong.stylish.di.catalog

import com.guanhong.stylish.repository.ProductListRepository
import com.guanhong.stylish.ui.catalog.CatalogContract
import com.guanhong.stylish.ui.catalog.CatalogFragment
import dagger.Module
import dagger.Provides

@Module
class CatalogPresenterModule {

    @Provides
    fun catalogFragmentModelProvider(): ProductListRepository{
        return ProductListRepository()
    }

    @Provides
    fun viewProvider(fragment: CatalogFragment): CatalogContract.View{
        return fragment
    }
}