package com.pioneerapp.catchgame_thread

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE  //화면 가로로 돌리기
        setContentView(R.layout.activity_main)
    }

    fun startGame(view: View?) {
        val intent = Intent(this, CatchActivity::class.java)
        startActivity(intent)
    }
}