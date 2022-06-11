package com.example.usspeak.repository

import com.example.usspeak.response.HistoryResponse
import com.example.usspeak.response.UserResponse
import okhttp3.MultipartBody

interface HistoryRepository {
    fun getHistory(
        token: String,
        onSuccess: (List<HistoryResponse.DataItem>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun uploadAudio(
        token: String,
        file: MultipartBody.Part,
        onSuccess: (UserResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}