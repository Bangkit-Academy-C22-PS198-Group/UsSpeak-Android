package com.example.usspeak.response

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String,
)