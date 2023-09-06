package com.altech.reift.productapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.altech.reift.core.domain.model.Product
import com.altech.reift.productapp.databinding.FragmentProductBinding
import com.altech.reift.productapp.databinding.ItemProductBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ProductRVAdapter: RecyclerView.Adapter<ProductRVAdapter.ProductViewHolder>() {

    var listProduct = arrayListOf<Product>();

    fun setProduct(list: List<Product>){
        listProduct.clear()
        listProduct.addAll(list)
    }
    class ProductViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = listProduct.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            with(listProduct[position]){
                tvTitle.text = title
                tvDesc.text = description
                tvPrice.text = price.toString()
                Glide.with(imgProduct.context)
                    .load(thumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(imgProduct)
            }
        }
    }
}