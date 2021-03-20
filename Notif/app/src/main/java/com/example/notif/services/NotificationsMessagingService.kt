package com.example.notif.services

import android.widget.Toast
import com.example.notif.model.DataBaseHandler
import com.example.notif.model.Notification
import com.google.firebase.messaging.RemoteMessage
import com.pusher.pushnotifications.fcm.MessagingService

class NotificationsMessagingService : MessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Toast.makeText(this,"notification received for ${remoteMessage.data["course"]}",Toast.LENGTH_LONG).show()
        val notify=Notification(null,null,null,null,null)
        notify.date=remoteMessage.data["date"]
        notify.title=remoteMessage.notification?.title
        notify.body=remoteMessage.notification?.body
        notify.course=remoteMessage.data["course"]
        var db=DataBaseHandler(this)//context maybe null
        db.insertData(notify,notify.course)
    }
}