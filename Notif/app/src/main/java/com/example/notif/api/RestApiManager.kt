package com.example.notif.api

import com.example.notif.model.AcNotify
import com.example.notif.model.CourseInfo
import com.example.notif.model.StudentInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiManager {
    fun addCourse(userData: CourseInfo, onResult: (CourseInfo?) -> Unit){
        val retrofit = RetrofitClient.buildService(Api::class.java)
        retrofit.checkCourse(userData).enqueue(
            object : Callback<CourseInfo> {
                override fun onFailure(call: Call<CourseInfo>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<CourseInfo>, response: Response<CourseInfo>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
    fun addStudent(user: StudentInfo, onResult: (StudentInfo?) -> Unit){
        val retrofit = RetrofitClient.buildService(Api::class.java)
        retrofit.addStudent(user).enqueue(
            object : Callback<StudentInfo> {
                override fun onFailure(call: Call<StudentInfo>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<StudentInfo>, response: Response<StudentInfo>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun loginStudent(user: StudentInfo, onResult: (StudentInfo?) -> Unit){
        val retrofit = RetrofitClient.buildService(Api::class.java)
        retrofit.loginStudent(user).enqueue(
            object : Callback<StudentInfo> {
                override fun onFailure(call: Call<StudentInfo>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<StudentInfo>, response: Response<StudentInfo>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun verify(user: AcNotify, onResult: (AcNotify?) -> Unit){
        val retrofit = RetrofitClient.buildService(Api::class.java)
        retrofit.verifyNotification(user).enqueue(
            object : Callback<AcNotify> {
                override fun onFailure(call: Call<AcNotify>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<AcNotify>, response: Response<AcNotify>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

}
