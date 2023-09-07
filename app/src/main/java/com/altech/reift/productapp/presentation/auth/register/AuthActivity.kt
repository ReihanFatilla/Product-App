package com.altech.reift.productapp.presentation.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.altech.reift.core.utils.AuthType
import com.altech.reift.productapp.MainActivity
import com.altech.reift.productapp.R
import com.altech.reift.productapp.databinding.ActivityRegisterBinding
import com.altech.reift.productapp.presentation.auth.AuthViewModel
import com.altech.reift.productapp.presentation.auth.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity: AuthActivity(Type.REGISTER)
class LoginActivity: AuthActivity(Type.LOGIN)

open class AuthActivity(val type: Type): AppCompatActivity() {

    private var _binding: ActivityRegisterBinding? = null

    private val binding get() = _binding as ActivityRegisterBinding

    private val viewModel: AuthViewModel by viewModel()

    private lateinit var client: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initGoogleSignClient()
        authResultObserver()
        setUpView()
    }

    private fun initGoogleSignClient() {
        val  options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        client = GoogleSignIn.getClient(this,options)
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
            btnAnonymous.setOnClickListener {
                viewModel.register(type = AuthType.Anonymous)
            }
            btnAuth.text = if(type == Type.LOGIN) "Login" else "Register"
            btnAuth.setOnClickListener{
                viewModel.register(type = AuthType.Custom(edtEmail.text.toString(), edtPassword.text.toString()))
            }
            tvAuth.text = if(type == Type.LOGIN) "Register." else "Login."
            tvAuth.setOnClickListener {
                if(type == Type.LOGIN) {
                    startActivity(Intent(this@AuthActivity, RegisterActivity::class.java))
                } else {
                    startActivity(Intent(this@AuthActivity, LoginActivity::class.java))
                }
                finish()
            }
            btnGoogle.setOnClickListener {
                startActivityForResult(client.signInIntent,10001)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==10001){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken,null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener{task->
                    if(task.isSuccessful){
                        val i  = Intent(this, MainActivity::class.java)
                        startActivity(i)

                    }else{
                        Toast.makeText(this,task.exception?.message,Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    enum class Type{
        REGISTER,
        LOGIN
    }
}