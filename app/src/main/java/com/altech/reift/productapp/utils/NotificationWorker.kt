package com.altech.reift.productapp.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.altech.reift.core.domain.model.Product
import com.altech.reift.productapp.R
import java.lang.Error

class NotificationWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        return try{
            makeStatusNotification(applicationContext)
            Result.success()
        } catch (e: Error){
            Result.failure()
        }
    }

    private fun makeStatusNotification(context: Context) {

        val CHANNEL_ID = "ROUTINE_PRODUCT_NOTIFICATION"
        val NOTIFICATION_ID = 0

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_product)
            .setContentTitle("See The Details Of${notificationProduct.title}!")
            .setContentText(notificationProduct.description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVibrate(LongArray(1))

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
        }
    }

    companion object {
        var notificationProduct = Product(
            id = 1,
            title = "iPhone 9",
            description = "An apple mobile which is nothing like apple",
            price = 549,
            discountPercentage = 12.96,
            rating = 4.69,
            stock = 94,
            category = "smartphones",
            thumbnail = "https://i.dummyjson.com/data/products/1/thumbnail.jpg",
            images = listOf(
                "https://i.dummyjson.com/data/products/1/1.jpg",
                "https://i.dummyjson.com/data/products/1/2.jpg",
                "https://i.dummyjson.com/data/products/1/3.jpg",
                "https://i.dummyjson.com/data/products/1/4.jpg",
                "https://i.dummyjson.com/data/products/1/thumbnail.jpg"
            )
        )
    }
}