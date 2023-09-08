package com.altech.reift.productapp.utils

import android.Manifest
import android.app.Notification.VISIBILITY_PUBLIC
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.altech.reift.core.domain.model.Product
import com.altech.reift.productapp.MainActivity
import com.altech.reift.productapp.R
import java.lang.Error
import java.util.concurrent.TimeUnit


class NotificationWorker(private val appContext: Context, workerParameters: WorkerParameters) : Worker(appContext, workerParameters) {
    override fun doWork(): Result {
        createReminderNotification(appContext)
        Log.i("doWorkss", "doWorkss: Kirim Notifikasi")
        return Result.success()
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

        val CHANNEL_ID = "ROUTINE_PRODUCT_NOTIFICATION"
        val NOTIFICATION_ID = 1
        fun createReminderNotification(context: Context) {
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(
                context, 0, intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            createNotificationChannel(context)

            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_product)
                .setContentTitle("See The Details Of ${notificationProduct.title}!")
                .setContentText(notificationProduct.description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent) // For launching the MainActivity
                .setAutoCancel(true) // Remove notification when tapped
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC) // Show on lock screen

            with(NotificationManagerCompat.from(context)) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    notify(NOTIFICATION_ID, builder.build())
                    return
                }
            }
        }

        private fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Daily Reminders"
                val descriptionText = "This channel sends daily reminders to add your transactions"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }

        fun scheduleNotificationWorker(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build()

            val periodicWorkRequest = PeriodicWorkRequest.Builder(
                NotificationWorker::class.java,
                15,
                TimeUnit.MINUTES
            )
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "notification_work",
                ExistingPeriodicWorkPolicy.REPLACE,
                periodicWorkRequest
            )
        }
    }


}