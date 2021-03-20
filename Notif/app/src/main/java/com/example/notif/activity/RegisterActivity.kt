package com.example.notif.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notif.R
import com.example.notif.api.RestApiManager
import com.example.notif.model.StudentInfo
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_register)
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        regisButton.setOnClickListener{
            val emailInput=registeremail?.text.toString()
            val pssd=registerpassword?.text.toString()
            val name=username?.text.toString()

            if (!validateEmail(emailInput)  or !validatePassword(pssd) or !validateName(name)) {
                Log.d("valid","fail")
                return@setOnClickListener
            }
            else{
                val apiService = RestApiManager()
                val userInfo = StudentInfo(
                    email = emailInput,
                    password = pssd,
                    userName = name,
                    validStudent = null,
                )
                apiService.addStudent(userInfo) {
                    if(it?.validStudent==true){
                        Toast.makeText(this,"Register successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,"email already registered",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        RestoLog.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun validateEmail(emailInput:String): Boolean {
        return if (emailInput.isEmpty()) {
            registeremail?.error="field can't be empty"
            false
        } /*else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail?.error = "Please enter a valid email address"
            false
        } */else {
            true
        }
    }

    private fun validatePassword(pssd:String): Boolean {
        return if (pssd.isEmpty()) {
            registerpassword?.error = "Field can't be empty"
            false

        } else {
            true
        }
    }

    private fun validateName(pssd:String): Boolean {
        return if (pssd.isEmpty()) {
            username?.error = "Field can't be empty"
            false

        } else {
            true
        }
    }

   /* private fun validStudent(eMail:String,pssd: String,name:String):Boolean {
        val apiService = RestApiManager()
        val userInfo = StudentInfo(
            email = eMail,
            password = pssd,
            userName = name,
            validStudent = null,
        )
        var stud:StudentInfo?=null
        apiService.addStudent(userInfo) {
            stud=it
        }

        return if(stud?.validStudent==true){
            true
        } else{
            false
        }

    }*/


}


