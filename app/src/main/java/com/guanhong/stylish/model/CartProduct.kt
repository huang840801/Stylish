package com.guanhong.stylish.model

import java.io.Serializable

class CartProduct : Serializable {
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var price: Int = 0
    var texture: String = ""
    var wash: String = ""
    var place: String = ""
    var note: String = ""
    var story: String = ""
    var stock: Int = 0
    var mainImage: String = ""
    var colorCode: String = ""
    var colorName: String = ""

    var selectedColorCode = ""
    var selectedSize = ""
    var selectedStock = 0
}