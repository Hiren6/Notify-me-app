package com.example.notif.model

import com.google.gson.annotations.SerializedName

data class StudentInfo (
    @SerializedName("userName") val userName:String?,
    @SerializedName("email") val email:String?,
    @SerializedName("password") val password:String?,
    @SerializedName("validStudent") val validStudent:Boolean?,
    )