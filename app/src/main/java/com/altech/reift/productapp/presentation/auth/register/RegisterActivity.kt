package com.altech.reift.productapp.presentation.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.altech.reift.core.utils.AuthType
import com.altech.reift.productapp.MainActivity
import com.altech.reift.productapp.R
import com.altech.reift.productapp.databinding.ActivityMainBinding
import com.altech.reift.productapp.databinding.ActivityRegisterBinding
import com.altech.reift.productapp.presentation.auth.AuthViewModel
import com.altech.reift.productapp.presentation.auth.login.LoginActivity
import com.altech.reift.productapp.presentation.product.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private var _binding: ActivityRegisterBinding? = null

    private val binding get() = _binding as ActivityRegisterBinding

    private val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authResultObserver()
        setUpView()
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
                viewModel.register(type = AuthType.Anonymous)
            }
            binding.btnRegister.setOnClickListener{
                viewModel.register(type = AuthType.Custom(edtEmail.text.toString(), edtPassword.text.toString()))
            }
            binding.tvLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}