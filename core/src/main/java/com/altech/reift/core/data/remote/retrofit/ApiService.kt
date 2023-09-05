package com.altech.reift.core.data.remote.retrofit

import com.altech.reift.core.data.remote.response.ProductResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products/category/{category}")
    fun getProductsByCategory(
        @Path("category")
        category: String,
    ): Flowable<ProductResponse>
}