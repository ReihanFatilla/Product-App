package com.altech.reift.productapp.presentation.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.altech.reift.productapp.R
import com.altech.reift.productapp.databinding.ActivityMainBinding

class RegisterActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}