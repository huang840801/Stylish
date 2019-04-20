package com.guanhong.stylish.ui.catalog.child

import com.guanhong.stylish.model.Product

interface CatalogChildContract {
    interface View{
        fun onBindProductList(productList: List<Product>)

    }
    interface Presenter{
        fun getProductList(itemType: String)
    }
}