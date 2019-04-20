package com.guanhong.stylish.ui.catalog

import com.guanhong.stylish.repository.ProductListRepository
import javax.inject.Inject

class CatalogPresenter @Inject constructor(
        private val repository: ProductListRepository,
        private val view: CatalogContract.View)
    : CatalogContract.Presenter {
}