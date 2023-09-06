package com.altech.reift.productapp.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.altech.reift.core.domain.model.Product
import com.altech.reift.core.utils.Utils.toWordCase

class ViewPagerAdapter(
    fa: FragmentActivity
):FragmentStateAdapter(fa) {

    private var listCategory = Product.Category.values()

    override fun getItemCount() = listCategory.size

    override fun createFragment(position: Int): Fragment {
        return Fragment().also {
            it.arguments = Bundle().also {
                it.putString(BUNDLE_CATEGORY, listCategory[0].name.toWordCase())
            }
        }
    }

    companion object {
        const val BUNDLE_CATEGORY = "category_bundle"
    }
}