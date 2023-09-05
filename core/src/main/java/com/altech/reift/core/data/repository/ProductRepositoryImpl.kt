package com.altech.reift.core.data.repository

import com.altech.reift.core.data.remote.RemoteDataSource
import com.altech.reift.core.domain.model.Product
import com.altech.reift.core.domain.repository.ProductRepository
import com.altech.reift.core.mapper.DataMapper.map
import io.reactivex.rxjava3.core.Flowable

class ProductRepositoryImpl(val remoteDataSource: RemoteDataSource): ProductRepository {
    override fun getProducts(category: Product.Category): Flowable<List<Product>> {
        return remoteDataSource.getProductsByCategory(category).map {
            it.map()
        }
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

}