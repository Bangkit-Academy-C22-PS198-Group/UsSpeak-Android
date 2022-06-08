package com.example.usspeak.repository

import com.example.usspeak.response.HistoryResponse

interface HistoryRepository {
    fun getHistory(
        token: String,
        onSuccess: (HistoryResponse?) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}