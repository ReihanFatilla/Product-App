package com.altech.reift.productapp.presentation.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.altech.reift.core.data.remote.response.ProductResponse
import com.altech.reift.core.domain.model.Product
import com.altech.reift.core.mapper.DataMapper.map
import com.altech.reift.productapp.R
import com.altech.reift.productapp.adapter.ProductVPAdapter
import com.altech.reift.productapp.databinding.ActivityDetailBinding
import com.altech.reift.productapp.databinding.ActivityMainBinding
import com.altech.reift.productapp.databinding.FragmentProductBinding
import com.altech.reift.productapp.presentation.product.ProductViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    private var _product: Product? = null
    private val product get() = _product as Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initProduct()
        setUpDetail()
    }

    private fun setUpDetail() {
        binding.apply {
            with(product) {
                tvTitle.text = title
                tvCategory.text = category
                tvPrice.text = price.toString()
                tvDesc.text = description
                Glide.with(imgProduct.context)
                    .load(thumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(imgProduct)
            }
            btnBack.setOnClickListener {
                finish()
            }
        }

    }

    private fun initProduct() {
        _product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(EXTRA_PRODUCT, Product::class.java)
        } else {
            intent?.getParcelableExtra(EXTRA_PRODUCT)
        } ?: ProductResponse().map().random()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_PRODUCT = "extra_product"
    }
}