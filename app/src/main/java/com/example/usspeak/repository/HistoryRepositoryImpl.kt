package com.example.usspeak.repository

import com.example.usspeak.api.ApiConfig
import com.example.usspeak.response.HistoryResponse
import com.example.usspeak.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryRepositoryImpl : HistoryRepository {

    override fun getHistory(
        token: String,
        onSuccess: (List<HistoryResponse.DataItem>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        ApiConfig.ApiService.getHistory(token).enqueue(object : Callback<HistoryResponse> {
            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        onSuccess(body.items)
                    }
                } else {
                    onFailure(Exception("Network Error"))
                }
            }

            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }

    override fun uploadAudio(
        token: String,
        file: MultipartBody.Part,
        onSuccess: (UserResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        ApiConfig.ApiService.uploadAudio(token, file).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        onSuccess(body)
                    }
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