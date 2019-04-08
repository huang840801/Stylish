package com.guanhong.stylish.`object`

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class Product(
        @SerializedName("id")var id: String,
        @SerializedName("title") var title: String,
        @SerializedName("description")  var description: String,
        @SerializedName("price")  var price: Int,
        @SerializedName("texture") var texture: String,
        @SerializedName("wash") var wash: String,
        @SerializedName("place") var place: String,
        @SerializedName("note") var note: String,
        @SerializedName("story") var story: String,
        @SerializedName("colors")var colors: List<Color> = listOf(),
        @SerializedName("sizes") var sizes: List<String> = listOf(),
        @SerializedName("variants") var variants: List<Variant> = listOf(),
        @SerializedName("main_image") var main_image: String,
        @SerializedName("images") var images: List<String> = listOf()
)