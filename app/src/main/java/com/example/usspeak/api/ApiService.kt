package com.example.usspeak.api

import com.example.usspeak.response.HistoryResponse
import com.example.usspeak.response.UserRequest
import com.example.usspeak.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("login")
    fun loginUser(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

    @POST("register")
    fun registerUser(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

    @GET("history")
    fun getHistory(
        @Header("Authorization") value: String
    ): Call<HistoryResponse>

    @Multipart
    @POST("upload")
    fun uploadAudio(
        @Header("Authorization") value: String,
        @Part multipart: MultipartBody.Part
    ): Call<UserResponse>

    @GET("profile")
    fun getUser(
        @Header("Authorization") value: String
    ): Call<UserResponse>

    @PUT("profile")
    fun renameUser(
        @Header("Authorization") value: String,
        @Body name: String
    ): Call<UserResponse>
}