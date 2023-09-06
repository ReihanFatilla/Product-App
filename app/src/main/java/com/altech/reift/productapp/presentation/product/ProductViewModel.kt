package com.altech.reift.productapp.presentation.product

import androidx.lifecycle.ViewModel
import com.altech.reift.core.domain.usecase.product.ProductUseCase

class ProductViewModel(val productUseCase: ProductUseCase): ViewModel() {
}