package com.example.usspeak.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usspeak.repository.HistoryRepository
import com.example.usspeak.repository.HistoryRepositoryImpl
import com.example.usspeak.response.HistoryResponse
import java.util.*

class HistoryViewModel : ViewModel() {
    private val historyRepository: HistoryRepository

    init {
        historyRepository = HistoryRepositoryImpl()
    }

    private val _observableHistory = MutableLiveData<HistoryResponse?>()
    val observableHistory: LiveData<HistoryResponse?>
        get() = _observableHistory

    private val _observableError = MutableLiveData<Throwable>()
    val observableError: LiveData<Throwable>
        get() = _observableError

    fun getHistory(token: String) {
        historyRepository.getHistory(token, onSuccess = {
            _observableHistory.value = it
        }) {
            _observableError.value = it
            _observableHistory.value = HistoryResponse("", Date(), "", "", "", "")
        }
    }
}