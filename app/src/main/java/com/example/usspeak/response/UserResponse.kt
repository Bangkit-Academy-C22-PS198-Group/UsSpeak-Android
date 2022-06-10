package com.example.usspeak.response

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("error")
    var error: String,

    @SerializedName("message")
    var message: String,

    @SerializedName("token")
    var token: String,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("picture")
    var picture: String? = null
)