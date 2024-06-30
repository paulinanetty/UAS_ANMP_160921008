package com.nmp.uts_anmp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nmp.uts_anmp.R
import com.nmp.uts_anmp.model.AppDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
            }
        })
}
val DB_NAME = "newdb"


fun buildDb(context: Context): AppDatabase {
    val db = AppDatabase.buildDatabase(context)
    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS hobbies (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, title TEXT, description TEXT, photoUrl TEXT NOT NULL)")
    }
}
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fname` TEXT, `lname` TEXT, `username` TEXT, `password` TEXT, `email` TEXT"
        )
    }
}


//class NotificationHelper(val context: Context
//) {
//    private val CHANNEL_ID = "todo_channel_id"
//    private val NOTIFICATION_ID = 1
//    companion object {
//        val REQUEST_NOTIF = 100
//    }
//
//    private fun createNotificationChannel(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT).apply { description = "Todo channel Description" }
//            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//
//        }
//
//    }
//
//    fun createNotification( title:String, message:String) {
//        createNotificationChannel()
//        val intent = Intent(context, MainActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
//                    Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        val pendingIntent = PendingIntent.getActivity(context,0, intent,
//            PendingIntent.FLAG_IMMUTABLE)
//
//        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.todochar)
//
//        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
//            .setSmallIcon(R.drawable.checklist)
//            .setLargeIcon(icon)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setStyle(
//                NotificationCompat.BigPictureStyle()
//                    .bigPicture(icon)
//                    .bigLargeIcon(null)
//            )
//            .setContentIntent(pendingIntent)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .build()
//        try {
//            NotificationManagerCompat.from(context)
//                .notify(NOTIFICATION_ID, notification)
//        } catch (e:SecurityException) {
//            Log.e("error", e.toString())
//        }
////        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) !=
////            PackageManager.PERMISSION_GRANTED) {
////            ActivityCompat.requestPermissions(activity,
////                arrayOf(Manifest.permission.POST_NOTIFICATIONS),REQUEST_NOTIF)
////            return
////        } else {
////            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
////        }
//
//    }
//
//}

