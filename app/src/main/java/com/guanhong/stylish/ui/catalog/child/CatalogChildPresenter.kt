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

    private var productList: List<Product> = listOf()
    private var hasNextPage = true
    private var paging: String? = null

    override fun getProductList(itemType: String) {

        if (hasNextPage) {

            view.showProgressBar()

            repository.getProductList(itemType, paging, object : DataResourceCallback.GetProductList {
                override fun onSuccess(response: ProductListResponse) {

                    if (response.paging == null) {
                        hasNextPage = false
                    }
                    productList += response.data

                    paging = response.paging
                    view.onBindProductList(productList)
                }
            })
        }
    }

    fun hasNextPage(): Boolean = hasNextPage
}