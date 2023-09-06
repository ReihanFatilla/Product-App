package com.altech.reift.productapp.adapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.altech.reift.core.domain.model.Product
import com.altech.reift.core.utils.Utils.toWordCase
import com.altech.reift.productapp.presentation.product.ProductFragment

class ProductVPAdapter(
    fa: FragmentActivity
):FragmentStateAdapter(fa) {

    private var listCategory = Product.Category.values()

    override fun getItemCount() = listCategory.size

    override fun createFragment(position: Int): Fragment {
        return ProductFragment().also { fragment ->
            fragment.arguments = Bundle().also {bundle ->
                bundle.putParcelable(BUNDLE_CATEGORY, listCategory[position])
            }
        }
    }

    companion object {
        const val BUNDLE_CATEGORY = "category_bundle"
    }
}