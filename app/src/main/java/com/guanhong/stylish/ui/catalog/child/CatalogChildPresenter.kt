package com.guanhong.stylish.ui.catalog.child

import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.model.response.ProductListResponse
import com.guanhong.stylish.repository.ProductListRepository
import javax.inject.Inject

class CatalogChildPresenter @Inject constructor(
        private val repository: ProductListRepository,
        private val view: CatalogChildContract.View)
    : CatalogChildContract.Presenter {

    override fun getProductList(itemType: String) {

        repository.getProductList(itemType, object : DataResourceCallback.GetProductList {
            override fun onSuccess(response: ProductListResponse) {
                val productList: List<Product> = response.data
                var paging: String? = response.paging

                view.onBindProductList(productList)
            }
        })
    }
}