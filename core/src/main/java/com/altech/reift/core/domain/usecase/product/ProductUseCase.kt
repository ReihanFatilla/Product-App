package com.altech.reift.core.domain.usecase.product

import com.altech.reift.core.domain.model.Product
import io.reactivex.rxjava3.core.Flowable

interface ProductUseCase {
    fun getProducts(category: Product.Category): Flowable<List<Product>>
    fun isAnonymous(): Boolean
    fun getUserDisplay(): String
    fun logout()
}