package com.example.usspeak.model.page

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.usspeak.api.TokenPref
import com.example.usspeak.databinding.ActivityLoginBinding
import com.example.usspeak.model.viewmodel.AuthViewModel
import com.example.usspeak.response.UserRequest

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AuthViewModel
    private lateinit var tokenPref: TokenPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(LayoutInflater.from(this), null, false)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.titleLogin.setTypeface(null, Typeface.BOLD)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        tokenPref = TokenPref(this)

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            val request = UserRequest(
                null,
                binding.edtEmail.text.toString().trim(),
                binding.edtPassword.text.toString().trim()
            )
            loginUser(request)
        }
    }

    private fun loginUser(request: UserRequest) {
        viewModel.loginUser(request)
        viewModel.observableUser.observe(this) { data ->
            val token = data.token
            tokenPref.setToken(token)

            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        }
        viewModel.observableError.observe(this) { data ->
            Toast.makeText(this@LoginActivity, data.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}