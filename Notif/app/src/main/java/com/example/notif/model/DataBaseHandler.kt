package com.example.notif.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
val DATABASE="Course-Notify"


class DataBaseHandler(context:Context?):SQLiteOpenHelper(context, DATABASE, null, 1) {
    private val NOTIF_ID="NOTIF_ID"
    private val DATE="DATE"
    private val BODY="BODY"
    private val TITLE="TITLE"

    override fun onCreate(db: SQLiteDatabase?) {
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    fun createTable(course:String){
        val db=this.writableDatabase
        val createTable = "CREATE TABLE IF NOT EXISTS " + course+ " (" + NOTIF_ID + " INTEGER PRIMARY KEY ," +TITLE+ "TEXT,"+ BODY + " TEXT," + DATE + " TEXT)"
        db?.execSQL(createTable)
    }
    fun insertData(notify:Notification,course:String?){
        val database=this.writableDatabase
        val content=ContentValues()
        content.put(NOTIF_ID,notify.notifId)
        content.put(TITLE,notify.title)
        content.put(BODY,notify.body)
        content.put(DATE,notify.date)
        database.insert(course,null,content)
    }
    fun readData(course: String?): MutableList<Notification> {
        val list: MutableList<Notification> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $course"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val notify = Notification(null,null,null,null,null)
                notify.notifId = result.getString(result.getColumnIndex(NOTIF_ID))
                notify.body = result.getString(result.getColumnIndex(BODY))
                notify.title = result.getString(result.getColumnIndex(TITLE))
                notify.date = result.getString(result.getColumnIndex(DATE))
                list.add(notify)
            }
            while (result.moveToNext())
        }
        result.close()
        return list
    }
}