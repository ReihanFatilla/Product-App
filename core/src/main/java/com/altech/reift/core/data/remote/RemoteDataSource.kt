package com.altech.reift.core.data.remote

import com.altech.reift.core.data.remote.response.ProductResponse
import com.altech.reift.core.data.remote.retrofit.ApiService
import com.altech.reift.core.domain.model.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.reactivex.rxjava3.core.Flowable

class RemoteDataSource(val apiService: ApiService) {
    fun getProductsByCategory(category: Product.Category): Flowable<ProductResponse> {
        val params = category.name.replace("_", "-")
        return apiService.getProductsByCategory(category = params)
    }

    fun firebaseAuth(): FirebaseAuth{
        return Firebase.auth
    }
}