package com.example.usspeak.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usspeak.repository.UserRepository
import com.example.usspeak.repository.UserRepositoryImpl
import com.example.usspeak.response.UserResponse

class UserViewModel: ViewModel() {
    private val userRepository: UserRepository

    init {
        userRepository = UserRepositoryImpl()
    }

    private val _observableUser = MutableLiveData<UserResponse?>()
    val observableUser : LiveData<UserResponse?>
        get() = _observableUser

    private val _observableUpdatedUser = MutableLiveData<UserResponse?>()
    val observableUpdatedUser : LiveData<UserResponse?>
        get() = _observableUpdatedUser

    private val _observableError = MutableLiveData<Throwable>()
    val observableError : LiveData<Throwable>
        get() = _observableError

    fun getUser(token: String) {
        userRepository.getUser(token, onSuccess = {
            _observableUser.value = it
        }) {
            _observableUser.value = null
            _observableError.value = it
        }
    }

    fun renameUser(name: String, token: String) {
        userRepository.renameUser(name, token, onSuccess = {
            _observableUpdatedUser.value = it
        }) {
            _observableUpdatedUser.value = null
            _observableError.value = it
        }
    }
}