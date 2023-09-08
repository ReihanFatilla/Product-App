package com.altech.reift.productapp

import android.app.Application
import com.altech.reift.core.di.dataSourceModule
import com.altech.reift.core.di.networkModule
import com.altech.reift.core.di.repositoryModule
import com.altech.reift.productapp.di.useCaseModule
import com.altech.reift.productapp.di.viewModelModule
import com.altech.reift.productapp.utils.NotificationWorker
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        NotificationWorker.scheduleNotificationWorker(this)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules( 
                listOf(
                    useCaseModule,
                    repositoryModule,
                    networkModule,
                    dataSourceModule,
                    viewModelModule
                )
            )
        }
    }
}