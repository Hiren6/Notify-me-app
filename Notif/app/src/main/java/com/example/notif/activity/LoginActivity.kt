package com.example.notif.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notif.R
import com.example.notif.api.RestApiManager
import com.example.notif.model.CourseInfo
import com.example.notif.model.StudentInfo
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.course.view.*

////NEED SPLASH SCREEENNN///
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
        var sp=getSharedPreferences("login", MODE_PRIVATE)

        loginButton.setOnClickListener{
            val emailInput=loginemail?.text.toString()
            val pssd=password?.text.toString()

            if (!validateEmail(emailInput)  or !validatePassword(pssd)) {
                Toast.makeText(this,"some fields are empty",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                val apiService = RestApiManager()
                val userInfo = StudentInfo(
                    email = null,
                    password = pssd,
                    userName = emailInput,
                    validStudent = null,
                )
                apiService.addStudent(userInfo) {
                    if(it?.validStudent==true){
                        sp.edit().putBoolean("logged",true).apply()
                        Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,"Invalid credentials",Toast.LENGTH_LONG).show()
                    }
                }

            }
        }

        logToRes.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateEmail(emailInput:String): Boolean {
        return if (emailInput.isEmpty()) {
            loginemail?.error = "Field can't be empty"
            false
        } else {
            true
        }
    }
    private fun validatePassword(pssd:String): Boolean {
        return if (pssd.isEmpty()) {
            password?.error = "Field can't be empty"
            false

        } else {
            true
        }
    }
}