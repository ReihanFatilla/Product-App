package com.altech.reift.core.di

import com.altech.reift.core.data.remote.RemoteDataSource
import com.altech.reift.core.data.remote.retrofit.ApiService
import com.altech.reift.core.data.repository.AuthRepositoryImpl
import com.altech.reift.core.data.repository.ProductRepositoryImpl
import com.altech.reift.core.domain.repository.AuthRepository
import com.altech.reift.core.domain.repository.ProductRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}

val dataSourceModule = module {
    factory { RemoteDataSource(get(), get()) }
}

val networkModule = module {
    single {
        Firebase.auth
    }

    single {
        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
                return@addInterceptor it.proceed(request)
            }
            .pingInterval(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}