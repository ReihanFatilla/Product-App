package com.altech.reift.core.domain.usecase.product

import com.altech.reift.core.domain.repository.ProductRepository

class ProductInteractor(val productRepository: ProductRepository): ProductUseCase {
    override fun getProducts() {
        TODO("Not yet implemented")
    }
}