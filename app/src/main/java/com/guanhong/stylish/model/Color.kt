package com.guanhong.stylish.model

import com.google.gson.annotations.SerializedName

data class Color(
        @SerializedName("name")   var name: String,
        @SerializedName("code") var code: String
)

