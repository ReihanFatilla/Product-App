package com.altech.reift.core.domain.usecase

import com.altech.reift.core.domain.repository.ProductRepository

class ProductInteractor(val productRepository: ProductRepository): ProductUseCase {
}