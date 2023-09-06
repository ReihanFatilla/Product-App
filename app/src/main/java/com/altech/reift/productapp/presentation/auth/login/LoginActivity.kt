package com.altech.reift.productapp.presentation.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.altech.reift.core.utils.AuthType
import com.altech.reift.productapp.MainActivity
import com.altech.reift.productapp.R
import com.altech.reift.productapp.databinding.ActivityLoginBinding
import com.altech.reift.productapp.databinding.ActivityMainBinding
import com.altech.reift.productapp.presentation.auth.AuthViewModel
import com.altech.reift.productapp.presentation.auth.register.RegisterActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null

    private val binding get() = _binding as ActivityLoginBinding

    private val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkSession()
        authResultObserver()
        setUpView()
    }

    private fun checkSession() {
        if(!viewModel.isAnonymous()){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun authResultObserver() {
        viewModel.authResultLiveData.observe(this){
            if(it.success){
                startActivity(Intent(this, MainActivity::class.java))
            }
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpView() {
        binding.apply {
            binding.btnAnonymous.setOnClickListener {
                viewModel.login(type = AuthType.Anonymous)
            }
            binding.btnLogin.setOnClickListener{
                viewModel.login(type = AuthType.Custom(edtEmail.text.toString(), edtPassword.text.toString()))
            }
            binding.tvRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}