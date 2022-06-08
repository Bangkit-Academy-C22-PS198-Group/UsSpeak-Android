package com.example.usspeak.model.page

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.usspeak.databinding.ActivityRegisterBinding
import com.example.usspeak.model.viewmodel.AuthViewModel
import com.example.usspeak.response.UserRequest

class RegisterActivity : AppCompatActivity() {
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(LayoutInflater.from(this), null, false)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.btnSignup.setOnClickListener {
            val request = UserRequest(
                binding.edtName.text.toString().trim(),
                binding.edtEmail.text.toString().trim(),
                binding.edtPassword.text.toString().trim()
            )
            registerUser(request)
        }
        playAnimation()
    }

    private fun registerUser(request: UserRequest) {
        viewModel.registerUser(request)
        viewModel.observableUser.observe(this) {
            val loginIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
        viewModel.observableError.observe(this) { data ->
            Toast.makeText(this@RegisterActivity, data.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun playAnimation(){
        val name = ObjectAnimator.ofFloat(binding.layoutName, View.ALPHA, 1F ).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.layoutEmail, View.ALPHA, 1F).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.layoutPassword, View.ALPHA, 1F).setDuration(500)

        AnimatorSet().apply {
            playTogether(name, email, password)
            startDelay = (500)
        }.start()

        ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1F).apply{
            duration = 1200
            startDelay = (1200)

        }.start()

    }
}