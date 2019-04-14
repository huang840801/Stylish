package com.guanhong.stylish.model.response

import com.google.gson.annotations.SerializedName
import com.guanhong.stylish.model.FacebookSignInData

data class FacebookResponse(
        @SerializedName("data") var data: FacebookSignInData
)