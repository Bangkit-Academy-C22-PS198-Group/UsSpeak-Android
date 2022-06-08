package com.example.usspeak.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usspeak.repository.UserRepository
import com.example.usspeak.repository.UserRepositoryImpl
import com.example.usspeak.response.UserRequest
import com.example.usspeak.response.UserResponse

class AuthViewModel(
//    private val userRepository: UserRepository
) : ViewModel() {

    private val userRepository: UserRepository

    init {
        userRepository = UserRepositoryImpl()
    }

    private val _observableUser = MutableLiveData<UserResponse>()
    val observableUser: LiveData<UserResponse>
        get() = _observableUser

    private val _observableError = MutableLiveData<Throwable>()
    val observableError: LiveData<Throwable>
        get() = _observableError

    fun loginUser(request: UserRequest) {
        userRepository.loginUser(request, onSuccess = {
            _observableUser.value = it
        }) {
            _observableUser.value = UserResponse("", "", "")
            _observableError.value = it
        }
    }

    fun registerUser(request: UserRequest) {
        userRepository.registerUser(request, onSuccess = {
            _observableUser.value = it
        }) {
            _observableUser.value = UserResponse("", "", "")
            _observableError.value = it
        }
    }
}