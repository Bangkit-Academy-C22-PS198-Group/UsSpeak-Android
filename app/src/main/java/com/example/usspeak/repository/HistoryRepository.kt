package com.example.usspeak.repository

import com.example.usspeak.response.HistoryResponse

interface HistoryRepository {
    fun getHistory(
        token: String,
        onSuccess: (List<HistoryResponse.DataItem>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}