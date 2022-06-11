package com.example.usspeak.repository

import com.example.usspeak.api.ApiConfig
import com.example.usspeak.response.UserRequest
import com.example.usspeak.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl : UserRepository {
    override fun loginUser(
        userRequest: UserRequest,
        onSuccess: (UserResponse?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        ApiConfig.ApiService.loginUser(userRequest).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    onSuccess(body)
                } else {
                    onFailure(Exception("Network Error"))
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }

    override fun registerUser(
        userRequest: UserRequest,
        onSuccess: (UserResponse?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        ApiConfig.ApiService.registerUser(userRequest).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    onSuccess(body)
                } else {
                    onFailure(Exception("Network Error"))
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }

    override fun getUser(
        token: String,
        onSuccess: (UserResponse?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        ApiConfig.ApiService.getUser(token).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    onSuccess(body)
                } else {
                    onFailure(Exception("Network Error"))
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }

    override fun renameUser(
        name: String,
        token: String,
        onSuccess: (UserResponse?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        ApiConfig.ApiService.renameUser(token, name).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    onSuccess(body)
                } else {
                    onFailure(Exception("Network Error"))
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}