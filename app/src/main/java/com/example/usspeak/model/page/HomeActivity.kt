package com.example.usspeak.model.page

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.usspeak.R
import com.example.usspeak.api.TokenPref
import com.example.usspeak.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var tokenPref: TokenPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(LayoutInflater.from(this), null, false)
        setContentView(binding.root)
        tokenPref = TokenPref(this)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupWithNavController(binding.navView, navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}