package com.example.marsphotos

import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class ServiceActivity : AppCompatActivity() {
    lateinit var  inboxListView:ListView
    private val SMS_PERMISSION_CODE = 101 //request code

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

    private fun checkSmsPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestSmsPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(
            android.Manifest.permission.READ_SMS),
            SMS_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //readSmsInbox()
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}