package com.guanhong.stylish.ui.catalog.child

import com.guanhong.stylish.model.Product

interface CatalogChildContract {
    interface View{
        fun onBindProductList(productList: List<Product>)
        fun showProgressBar()
    }
    interface Presenter{
        fun getProductList(itemType: String)
    }
}