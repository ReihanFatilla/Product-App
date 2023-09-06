package com.altech.reift.productapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.altech.reift.core.domain.model.Product
import com.altech.reift.core.utils.Utils.toWordCase
import com.altech.reift.productapp.adapter.ViewPagerAdapter
import com.altech.reift.productapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        binding.apply {
            vpProduct.adapter = ViewPagerAdapter(this@MainActivity)
            TabLayoutMediator(tabProduct, vpProduct){ tab, position ->
                tab.text = Product.Category.values()[position].name.toWordCase()
            }.attach()
        }
    }
}