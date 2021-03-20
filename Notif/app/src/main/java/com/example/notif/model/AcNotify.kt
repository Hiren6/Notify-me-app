package com.example.notif.model

import com.google.gson.annotations.SerializedName

data class AcNotify (
    @SerializedName("notifId") var notifId:String?,
    @SerializedName("userName") var userName:String?,
)