package com.guanhong.stylish.model

import com.google.gson.annotations.SerializedName

data class FacebookSignInData(
        @SerializedName("access_token") var accessToken: String,
        @SerializedName("access_expired") var accessExpired: Int,
        @SerializedName("user") var user: User
)