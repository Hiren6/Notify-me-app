package com.example.notif.model

import com.google.gson.annotations.SerializedName

data class Notification (
    var notifId:String?,
    var course:String?,
    var date:String?,
    var body:String?,
    var title:String?,
)