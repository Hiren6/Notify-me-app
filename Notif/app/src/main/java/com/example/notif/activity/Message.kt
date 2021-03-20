package com.example.notif.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notif.R
import kotlinx.android.synthetic.main.activity_message.*

class Message : AppCompatActivity() {
    var body:String?=null
    var date:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        body=intent.getStringExtra("body")
        date=intent.getStringExtra("date")
        messageBody.text = body
    }
}