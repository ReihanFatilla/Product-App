package com.altech.reift.core.data.repository

import android.util.Log
import com.altech.reift.core.data.remote.RemoteDataSource
import com.altech.reift.core.domain.model.Product
import com.altech.reift.core.domain.repository.ProductRepository
import com.altech.reift.core.mapper.DataMapper.map
import io.reactivex.rxjava3.core.Flowable
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ProductRepositoryImpl(val remoteDataSource: RemoteDataSource) : ProductRepository {

    val auth = remoteDataSource.firebaseAuth

    override fun getProducts(category: Product.Category): Flowable<List<Product>> {
        return remoteDataSource.getProductsByCategory(category).map {
            it.map()
        }
    }

    override fun isAnonymous(): Boolean {
        return auth.currentUser?.isAnonymous ?: true
    }

    override fun getUserDisplay(): String {
        return auth.currentUser?.displayName ?: auth.currentUser?.email ?: "User"
    }

    override fun logout() {
        auth.signOut()
    }

}