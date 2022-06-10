package com.example.usspeak.repository

import com.example.usspeak.response.UserRequest
import com.example.usspeak.response.UserResponse

interface UserRepository {
    fun loginUser(
        userRequest: UserRequest,
        onSuccess: (UserResponse?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun registerUser(
        userRequest: UserRequest,
        onSuccess: (UserResponse?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getUser(
        token: String,
        onSuccess: (UserResponse?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun renameUser(
        name: String,
        token: String,
        onSuccess: (UserResponse?) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}