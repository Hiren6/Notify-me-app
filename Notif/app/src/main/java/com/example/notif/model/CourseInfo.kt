package com.example.notif.model

import com.google.gson.annotations.SerializedName

data class CourseInfo(
    @SerializedName("courseId") val courseId:String?,//PRIMARY KEY
    @SerializedName("courseKey") val courseKey: String?,// ----TO CHANGE VAR NAME
    @SerializedName("courseName") val courseName: String?,// ----TO CHANGE VAR NAME
    @SerializedName("validStudent") val success: Boolean?,// ----TO CHANGE VAR NAME
)
