package com.guanhong.stylish.model.response

import com.google.gson.annotations.SerializedName
import com.guanhong.stylish.model.Product

data class ProductListResponse(
        @SerializedName("data") var data: List<Product>,
        @SerializedName("paging") var paging: String?
)

