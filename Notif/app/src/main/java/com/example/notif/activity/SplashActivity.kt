package com.example.notif.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.notif.R
import com.example.notif.api.RestApiManager
import com.example.notif.model.AcNotify
import com.example.notif.model.DataBaseHandler
import com.example.notif.model.Notification
import kotlinx.android.synthetic.main.activity_register.*

class SplashActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        supportActionBar!!.hide()
        Handler().postDelayed({

            val notify= Notification(null,null,null,null,null)
            notify.notifId=intent.getStringExtra("notifId")
            notify.course=intent.getStringExtra("course")
            notify.date=intent.getStringExtra("date")
            notify.body=intent.getStringExtra("body")
            notify.title=intent.getStringExtra("title")
            if(notify.course!=null){
                Log.d("courses",notify.course!!)
                var note=AcNotify(null,null)
               // val user=username.text.toString()
               // note.userName=user
                note.notifId=notify.notifId

              /*  val apiService=RestApiManager()
                apiService.verify(note){
                }*/

                var db=DataBaseHandler(this)
                db.insertData(notify,notify.course)
                val intent=Intent(this,NotifList::class.java)
                intent.putExtra("course_name",notify.course)
                startActivity(intent)
            }

            var sp=getSharedPreferences("login", MODE_PRIVATE)
            if(sp.getBoolean("logged",false)){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                //finish()
            }
            else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }, 2000) // 2000 is the delayed time in milliseconds.


    }
}