package com.altech.reift.productapp.presentation.auth.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.altech.reift.productapp.R
import com.altech.reift.productapp.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}