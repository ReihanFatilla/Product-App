package com.altech.reift.core.data.remote

import com.altech.reift.core.data.remote.response.ProductResponse
import com.altech.reift.core.data.remote.retrofit.ApiService
import com.altech.reift.core.domain.model.Product
import io.reactivex.rxjava3.core.Flowable

class RemoteDataSource(val apiService: ApiService) {
    fun getProductsByCategory(category: Product.Category): Flowable<ProductResponse> {
        val params = category.name.replace("_", "-")
        return apiService.getProductsByCategory(category = params)
    }
}