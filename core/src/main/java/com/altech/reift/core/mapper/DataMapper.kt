package com.altech.reift.core.mapper

import com.altech.reift.core.data.remote.response.ProductResponse
import com.altech.reift.core.data.remote.response.ProductsItem
import com.altech.reift.core.domain.model.Product

object DataMapper {
    fun ProductResponse.map(): List<Product> {
        return products.orEmpty().map {
            with(it ?: ProductsItem()) {
                Product(
                    discountPercentage = discountPercentage ?: 0.0,
                    thumbnail = thumbnail.orEmpty(),
                    images = images.orEmpty().map { it.orEmpty() },
                    price = price ?: 0,
                    rating = rating ?: 0.0,
                    description = description.orEmpty(),
                    id = id ?: 0,
                    title = title.orEmpty(),
                    stock = stock ?: 0,
                    category = category.orEmpty(),
                )
            }

        }
    }
}