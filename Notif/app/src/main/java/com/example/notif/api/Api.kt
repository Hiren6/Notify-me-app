package com.example.notif.api

import com.example.notif.model.AcNotify
import com.example.notif.model.CourseInfo
import com.example.notif.model.StudentInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("student/new_course/")
    fun checkCourse(@Body userData: CourseInfo) :Call<CourseInfo>

    @POST("/student/register/")
    fun addStudent(@Body studentData:StudentInfo) : Call<StudentInfo>

    @POST("student/login/")
    fun loginStudent(@Body studentData:StudentInfo) : Call<StudentInfo>

    @POST("/stud")
    fun verifyNotification(@Body notify: AcNotify) : Call<AcNotify>
}