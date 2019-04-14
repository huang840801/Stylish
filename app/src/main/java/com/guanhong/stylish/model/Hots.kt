package com.guanhong.stylish.model

import com.google.gson.annotations.SerializedName

data class Hots(
        @SerializedName("title") var title: String,
        @SerializedName("products") var products: List<Product> = listOf()
)