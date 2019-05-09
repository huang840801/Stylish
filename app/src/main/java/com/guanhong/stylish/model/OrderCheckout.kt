package com.guanhong.stylish.model

class OrderCheckout {
    var prime = ""
    var order = Order()
    var productList = listOf<OrderProduct>()
}

class Order {
    var shipping = ""
    var payment = ""
    var subtotal = 0
    var freight = 0
    var total = 0
    var recipient = Recipient()
}

class Recipient {
    var name = ""
    var phone = ""
    var email = ""
    var address = ""
    var time = ""
}

class OrderProduct {
    var id = ""
    var name = ""
    var price = 0
    var orderColor = OrderColor()
    var size = ""
    var qty = 0
}

class OrderColor {
    var code = ""
    var name = ""
}