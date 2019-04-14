package com.guanhong.stylish.model

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id") var id: String,
        @SerializedName("providers") var providers: String,
        @SerializedName("name") var name: String,
        @SerializedName("email") var email: String,
        @SerializedName("picture") var picture: String
)