package com.guanhong.stylish.model

import com.google.gson.annotations.SerializedName

data class Variant(
        @SerializedName("color_code")  var color_code: String,
        @SerializedName("size")  var size: String,
        @SerializedName("stock")  var stock: Int
)