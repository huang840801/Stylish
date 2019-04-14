package com.guanhong.stylish.util

class ApiConfig {
    companion object {

        const val HTTP = "https://"
        const val API = "/api"
        const val HOST_NAME = "api.appworks-school.tw"
        const val API_VERSION = "/1.0"
        const val MARKETING = "/marketing"
        const val CAMPAIGNS = "/campaigns"
        const val HOTS = "/hots"
        const val USER = "/user"
        const val SIGNIN = "/signin"

        const val MARKETING_HOTS_API = HTTP + HOST_NAME + API + API_VERSION + MARKETING + HOTS
        const val USER_SIGN_IN_API = HTTP + HOST_NAME + API + API_VERSION + USER + SIGNIN

        const val data = "data"
        const val title = "title"
        const val products = "products"
        const val id = "id"
        const val description = "description"
        const val price = "price"
        const val texture = "texture"
        const val wash = "wash"
        const val place = "place"
        const val note = "note"
        const val story = "story"
        const val colors = "colors"
        const val code = "code"
        const val name = "name"
        const val variants = "variants"
        const val color_code = "color_code"
        const val size = "size"
        const val stock = "stock"
        const val main_image = "main_image"
        const val images = "images"
    }
}