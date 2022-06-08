package com.example.usspeak.api

import com.example.usspeak.response.UserRequest
import com.example.usspeak.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun loginUser(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

    @POST("register")
    fun registerUser(
        @Body userRequest: UserRequest
    ): Call<UserResponse>
}