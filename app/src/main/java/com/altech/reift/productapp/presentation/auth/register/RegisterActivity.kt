package com.altech.reift.productapp.presentation.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.altech.reift.productapp.R
import com.altech.reift.productapp.databinding.ActivityMainBinding
import com.altech.reift.productapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private var _binding: ActivityRegisterBinding? = null

    private val binding get() = _binding as ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}