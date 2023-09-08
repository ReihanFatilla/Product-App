package com.altech.reift.productapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.altech.reift.core.domain.model.Product
import com.altech.reift.core.utils.Utils.toWordCase
import com.altech.reift.productapp.adapter.ProductVPAdapter
import com.altech.reift.productapp.databinding.ActivityMainBinding
import com.altech.reift.productapp.presentation.auth.AuthActivity
import com.altech.reift.productapp.presentation.product.ProductViewModel
import com.altech.reift.productapp.utils.NotificationWorker
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private val viewModel: ProductViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
        setUpViewPager()
    }

    private fun setUpView() {
        binding.apply {
            tvDisplay.text = viewModel.getUserDisplay()
            btnLogout.setOnClickListener {
                viewModel.logout()
                startActivity(Intent(this@MainActivity, AuthActivity.LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun setUpViewPager() {
        binding.apply {
            vpProduct.adapter = ProductVPAdapter(this@MainActivity)
            TabLayoutMediator(tabProduct, vpProduct) { tab, position ->
                tab.text = Product.Category.values()[position].name.toWordCase()
            }.attach()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}