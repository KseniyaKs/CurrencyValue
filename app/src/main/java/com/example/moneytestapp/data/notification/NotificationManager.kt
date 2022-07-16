package com.example.moneytestapp.data.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.SparseArray
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_LOW
import com.example.moneytestapp.MainActivity
import com.example.moneytestapp.R
import java.lang.ref.WeakReference
import javax.inject.Inject

//interface NotificationManager {
//    fun showNotificationWithContinuousUpdate(id: Int, title: String): MessageChanger
//    fun dismissNotificationWithId(id: Int)
//
//    interface MessageChanger {
//        fun changeMessage(message: String)
//    }
//
//}
//
//class AndroidNotificationManager @Inject constructor(
//    private val context: Context
//) : NotificationManager {
//
//    companion object {
//        const val NOTIFICATION_CHANNEL_WITH_LOW_PRIORITY_ID = "low priority"
//    }
//
//    private val notificationManager =
//        context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
//
//    private val notifications = SparseArray<NotificationCompat.Builder>()
//
//    override fun showNotificationWithContinuousUpdate(
//        id: Int,
//        title: String
//    ): NotificationManager.MessageChanger {
//        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_WITH_LOW_PRIORITY_ID)
//            .setContentTitle(title)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setAutoCancel(true)
//            .setOngoing(true)
//            .setPriority(PRIORITY_LOW)
//            .setOnlyAlertOnce(true)
//            .setContentIntent(
//                PendingIntent.getActivity(
//                    context,
//                    0,
//                    Intent(context, MainActivity::class.java).apply {
//                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//                    },
//                    0
//                )
//            )
//
//        notifications.put(id, builder)
//        notificationManager.notify(id, builder.build())
//
//        val weakRefNotificationManager = WeakReference(notificationManager)
//        val weakRefNotificationBuilders = WeakReference(notifications)
//
//        return object : NotificationManager.MessageChanger {
//            override fun changeMessage(message: String) {
//                val builder = weakRefNotificationBuilders.get()?.get(id)
//                if (builder != null) {
//                    builder.setSubText(message)
//                    weakRefNotificationManager.get()?.notify(id, builder.build())
//                }
//            }
//        }
//    }
//
//    override fun dismissNotificationWithId(id: Int) {
//        notificationManager.cancel(id)
//        notifications.delete(id)
//    }
//}