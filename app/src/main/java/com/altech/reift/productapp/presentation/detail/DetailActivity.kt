package com.altech.reift.productapp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.altech.reift.productapp.R
import com.altech.reift.productapp.databinding.ActivityDetailBinding
import com.altech.reift.productapp.databinding.ActivityMainBinding
import com.altech.reift.productapp.databinding.FragmentProductBinding

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}