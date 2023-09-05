package com.altech.reift.core.domain.usecase.product

import com.altech.reift.core.domain.model.Product
import com.altech.reift.core.domain.repository.ProductRepository
import io.reactivex.rxjava3.core.Flowable

class ProductInteractor(val productRepository: ProductRepository): ProductUseCase {
    override fun getProducts(category: Product.Category): Flowable<List<Product>> {
        return productRepository.getProducts(category)
    }

    override fun logout() {
        return productRepository.logout()
    }
}