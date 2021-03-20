package com.example.notif.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notif.R
import com.example.notif.model.DataBaseHandler
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.content_scrolling.view.*

class NotifList : AppCompatActivity() {
    var db:DataBaseHandler?=null
    var course_title:String?=null
    var listScroll:LinearLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notif_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        course_title=intent.getStringExtra("course_name")
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title =course_title
        Log.d("notifList",course_title!!)
        listScroll=findViewById(R.id.list_scroll)
        addNotify()
    }
    private fun addNotify(){
        var db=DataBaseHandler(this)
        val notifyList=db.readData(course_title)
       if(notifyList.size==0){
            Toast.makeText(this,"NO DATA",Toast.LENGTH_LONG).show()
        }
        else {
            for (item in notifyList) {
                val inflater =
                    getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater//to inflate
                val cor = inflater.inflate(R.layout.content_scrolling, null)
                cor.notify_title.text = item.title
                cor.notify_date.text = item.date
                cor.message.text=item.body
                listScroll!!.addView(cor, listScroll!!.childCount - 1)
            }
        }
    }

    fun viewNotification(view: View) {

        var body=view.message.text.toString()
        val intent=Intent(this,Message::class.java)
        intent.putExtra("message_body",body)
        startActivity(intent)
    }


}