package com.example.marsphotos

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity


class ServiceActivity : AppCompatActivity() {
    lateinit var  inboxListView:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
         inboxListView = findViewById(R.id.listViewinbox)

        val uriSms = Uri.parse("content://sms/inbox")
        val cursor: Cursor? = getContentResolver().query(uriSms, null, null, null, null)
        //adapter design pattern --13 cols --listview contains only 2 textview in each row
       var columnNames = arrayOf("address","body")
        var textViewIds = intArrayOf(android.R.id.text1, android.R.id.text2)
        val myAdapter:SimpleCursorAdapter = SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,columnNames,textViewIds)
        inboxListView.adapter = myAdapter
    }

    fun startMusicService(view: View) {
        var mIntent = Intent(this,MusicService::class.java)
        mIntent.putExtra("musicfile","https://nameofthefile")
        startService(mIntent)
    }

    fun stoptMusicService(view: View) {
        var mIntent = Intent(this,MusicService::class.java)
        stopService(mIntent)
    }
}