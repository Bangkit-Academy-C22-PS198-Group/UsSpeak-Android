package com.example.usspeak.repository

import com.example.usspeak.api.ApiConfig
import com.example.usspeak.response.HistoryResponse
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
}