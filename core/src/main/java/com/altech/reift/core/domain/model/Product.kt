package com.altech.reift.core.domain.model

import com.google.gson.annotations.SerializedName

data class Product(
    val discountPercentage: Double,
    val thumbnail: String,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val description: String,
    val id: Int,
    val title: String,
    val stock: Int,
    val category: String,
) {
    enum class Category {
        SMARTPHONES,
        LAPTOPS,
        FRAGRANCES,
        SKINCARE,
        GROCERIES,
        HOME_DECORATION,
        FURNITURE,
        TOPS,
        WOMENS_DRESSES,
        WOMENS_SHOES,
        MENS_SHIRTS,
        MENS_SHOES,
        MENS_WATCHES,
        WOMENS_WATCHES,
        WOMENS_BAGS,
        WOMENS_JEWELLERY,
        SUNGLASSES,
        AUTOMOTIVE,
        MOTORCYCLE,
        LIGHTING
    }
}
