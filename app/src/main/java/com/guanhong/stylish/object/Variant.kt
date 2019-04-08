package com.guanhong.stylish.`object`

import com.google.gson.annotations.SerializedName

class Variant(
        @SerializedName("color_code")  var color_code: String,
        @SerializedName("size")  var size: String,
        @SerializedName("stock")  var stock: Int
)