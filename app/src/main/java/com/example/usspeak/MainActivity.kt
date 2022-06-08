package com.example.usspeak

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.usspeak.api.TokenPref
import com.example.usspeak.databinding.ActivityMainBinding
import com.example.usspeak.model.page.HomeActivity
import com.example.usspeak.model.page.LoginActivity

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var tokenPref: TokenPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this), null, false)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.tvUsspeak.setTypeface(null, Typeface.BOLD)
        tokenPref = TokenPref(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (tokenPref.getToken() == "") {
                val loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()
            } else {
                val homeIntent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(homeIntent)
                finish()
            }
        }, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}