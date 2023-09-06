package com.altech.reift.productapp.di

import com.altech.reift.core.domain.usecase.auth.AuthInteractor
import com.altech.reift.core.domain.usecase.product.ProductInteractor
import com.altech.reift.productapp.presentation.product.ProductViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { ProductViewModel(get()) }
}

val useCaseModule = module{
    factory { AuthInteractor(get()) }
    factory { ProductInteractor(get()) }
}