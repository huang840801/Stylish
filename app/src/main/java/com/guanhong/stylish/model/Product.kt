package com.guanhong.stylish.model

import java.util.*
import kotlin.collections.ArrayList

class Product {
    var id = 0
    var title = ""
    var description = ""
    var price = 0
    var texture = ""
    var wash = ""
    var place = ""
    var note = ""
    var story = ""
    var colors = ArrayList<Color>()
    var sizes = ArrayList<String>()
    var variants = ArrayDeque<Variant>()
    var main_image = ""
    var images = ArrayList<String>()
}