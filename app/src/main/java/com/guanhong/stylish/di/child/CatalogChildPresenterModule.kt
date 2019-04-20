package com.guanhong.stylish.di.child

import com.guanhong.stylish.repository.ProductListRepository
import com.guanhong.stylish.ui.catalog.child.CatalogChildContract
import com.guanhong.stylish.ui.catalog.child.CatalogChildFragment
import dagger.Module
import dagger.Provides

@Module
class CatalogChildPresenterModule {

    @Provides
    fun catalogChildFragmentModelProvider(): ProductListRepository {
        return ProductListRepository()
    }

    @Provides
    fun viewProvider(fragment: CatalogChildFragment): CatalogChildContract.View {
        return fragment
    }
}