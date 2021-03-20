package com.example.notif.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.notif.R
import com.example.notif.api.RestApiManager
import com.example.notif.model.CourseInfo
import com.example.notif.model.DataBaseHandler
import com.pusher.pushnotifications.PushNotifications
import kotlinx.android.synthetic.main.add_course.view.*
import kotlinx.android.synthetic.main.course.view.*


class MainActivity : AppCompatActivity() {
    private var parentLinearLayout: LinearLayout? = null
    var db:DataBaseHandler?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PushNotifications.start(applicationContext, "e5e2e6cd-9f3b-449e-a850-3931a46fedc0")//changes made
        PushNotifications.addDeviceInterest("hello")
        parentLinearLayout = findViewById(R.id.parent_layout)
        db=DataBaseHandler(this)
    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menubar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                Toast.makeText(this, "search was selected", Toast.LENGTH_SHORT)
                    .show()//here write search function
                return true
            }
            R.id.add -> {
                openDialog()
                return true
            }

            R.id.settings -> {
                Toast.makeText(this, "settings was selected", Toast.LENGTH_SHORT)
                    .show()//3dont know aht to write here
                return true
            }
            R.id.profile -> {
                Toast.makeText(this, "profile was selected", Toast.LENGTH_SHORT)
                    .show()//4 dont know what to write here either
                return true
            }
            R.id.logOut -> {
                PushNotifications.clearAllState()
                val sp = getSharedPreferences("login", MODE_PRIVATE)
                sp.edit().putBoolean("logged", false).apply()
                val intent = Intent(this, LoginActivity::class.java)
                Toast.makeText(this, "logout successfully", Toast.LENGTH_SHORT).show()//4 dont know what to write here either
                startActivity(intent)
                finish()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
}

    private fun openDialog() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater//to inflate
        val dialogView = inflater.inflate(R.layout.add_course, null)

        val mBuilder=AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Add Course")
        val mAlertDialog=mBuilder.show()

        dialogView.cancel_register.setOnClickListener{
            mAlertDialog.dismiss()
        }

        dialogView.course_register.setOnClickListener{
            val coursename=dialogView.course_name.text.toString()
            val coursekey=dialogView.course_key.text.toString()

            if(coursename.isEmpty()){
                dialogView.course_name.error = "course name required"
                return@setOnClickListener
            }

            if(coursekey.isEmpty()){
                dialogView.course_key.error = "course key required"
                return@setOnClickListener
            }

            /*if(registerToCourse(coursename, coursekey)){
                PushNotifications.addDeviceInterest(coursename)
                Log.d("pusher",coursename)
                mAlertDialog.dismiss()
            }else{
                return@setOnClickListener
            }*/
            else{
                val apiService = RestApiManager()
                val userInfo = CourseInfo(
                    courseName = null,
                    courseKey = coursekey,
                    success = null,
                    courseId = coursename,
                )
                apiService.addCourse(userInfo) {
                    Log.d("fffff",it?.success.toString())
                    if (it?.success ==true) {//have to change to boolean
                        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater//to inflate
                        val cor=inflater.inflate(R.layout.course, null)
                        cor.cname.setText(coursename)
                        cor.fullname.setText(it?.courseName)
                        parentLinearLayout!!.addView(cor, parentLinearLayout!!.childCount - 1)
                        db?.createTable(coursename)
                        PushNotifications.addDeviceInterest(coursename)
                        mAlertDialog.dismiss()
                        Toast.makeText(applicationContext, "Registration successful", Toast.LENGTH_SHORT).show()

                    } else {
                        Log.d("fffdddd","inside2")
                        Toast.makeText(applicationContext, "course-key mismatch", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
        return
    }

  /* private fun registerToCourse(cName: String, ckey: String):Boolean {
        val apiService = RestApiManager()
        val userInfo = CourseInfo(
            courseName = null,
            courseKey = ckey,
            success = null,
            courseId = cName,
        )
       Log.d("course",userInfo.toString())
       var course=true
       apiService.addCourse(userInfo) {
            Log.d("fffdddd",it?.success.toString())
            if (it?.success ==null) {//have to change to boolean
                val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater//to inflate
                val cor=inflater.inflate(R.layout.course, null)
                cor.cname.setText(cName)
                cor.fullname.setText(it?.courseName)
                parentLinearLayout!!.addView(cor, parentLinearLayout!!.childCount - 1)
                db?.createTable(cName)
                Toast.makeText(applicationContext, "Registration successful", Toast.LENGTH_SHORT).show()
                Log.d("fffdddd","inside1")

                course=true
            } else {
                Log.d("fffdddd","inside2")
                Toast.makeText(applicationContext, "course-key mismatch", Toast.LENGTH_SHORT).show()
            }
        }
       Log.d("fffdddd","Outside")
       return course
    }*/

   fun openNotification(view: View?){
       val courseName=view?.cname!!.text.toString()
       val intent=Intent(this,NotifList::class.java)
       intent.putExtra("course_name",courseName)
       startActivity(intent)
   }
}
