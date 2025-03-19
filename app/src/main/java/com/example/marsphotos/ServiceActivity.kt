package com.example.marsphotos

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

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