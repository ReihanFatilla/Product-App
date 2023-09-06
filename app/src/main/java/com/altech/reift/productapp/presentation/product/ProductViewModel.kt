package com.altech.reift.productapp.presentation.product

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.altech.reift.core.domain.model.Product
import com.altech.reift.core.domain.usecase.product.ProductUseCase

class ProductViewModel(val productUseCase: ProductUseCase): ViewModel() {

    val productsLiveData = MediatorLiveData<List<Product>>()

    fun logout() {
        productUseCase.logout()
    }
    suspend fun isAnonymous(): Boolean {
        return productUseCase.isAnonymous()
    }

    fun getProducts(category: Product.Category){
        val source = LiveDataReactiveStreams.fromPublisher(
            productUseCase.getProducts(category)
        )

        productsLiveData.addSource(source){
            productsLiveData.postValue(it)
            productsLiveData.removeSource(source)
        }
    }
}