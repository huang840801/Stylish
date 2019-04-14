package com.guanhong.stylish.model.response

import com.google.gson.annotations.SerializedName
import com.guanhong.stylish.model.Hots

data class HotResponse (
        @SerializedName("data") var data: List<Hots>
)