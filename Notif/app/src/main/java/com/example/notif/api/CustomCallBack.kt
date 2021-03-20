package com.example.notif.api

interface CustomCallBack {
    fun <T>onSucess(value: Result<T>)
    fun onFailure()
}