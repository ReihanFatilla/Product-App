package com.altech.reift.productapp.presentation.product

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.altech.reift.core.domain.model.Product
import com.altech.reift.productapp.R
import com.altech.reift.productapp.adapter.ProductRVAdapter
import com.altech.reift.productapp.adapter.ProductVPAdapter
import com.altech.reift.productapp.databinding.ActivityMainBinding
import com.altech.reift.productapp.databinding.FragmentProductBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding as FragmentProductBinding

    private val viewModel: ProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductBinding.inflate(layoutInflater)

        fetchProduct()
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {

        val mAdapter = ProductRVAdapter()
        binding.rvProduct.layoutManager = LinearLayoutManager(context)



        viewModel.productsLiveData.observe(viewLifecycleOwner) {list ->
            mAdapter.setProduct(list)
            binding.rvProduct.adapter = mAdapter
        }
    }

    private fun fetchProduct() {
        val category = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ProductVPAdapter.BUNDLE_CATEGORY, Product.Category::class.java)
        } else {
            arguments?.getParcelable(ProductVPAdapter.BUNDLE_CATEGORY)
        } ?: Product.Category.LAPTOPS

        viewModel.getProducts(category = category)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}