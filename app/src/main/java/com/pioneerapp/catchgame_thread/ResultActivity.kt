package com.pioneerapp.catchgame_thread

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import java.lang.Exception

class ResultActivity : AppCompatActivity() {

    var score = 0
    var highScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION




        //presentScore
        score = intent.getStringExtra("SCORE")!!.toInt()
        var tv = findViewById<TextView>(R.id.present_score)
        tv.text = score.toString()
        saveData()

        //HighScore
        loadData()


        findViewById<Button>(R.id.back_btn).setOnClickListener {
            finish()
        }
    }

    fun saveData() {

        val sharedPreferences  = getSharedPreferences("sharedPrefs10", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        loadData()
        if(score > highScore) {
            editor?.putString("GAME_SCORE", score.toString())
            editor?.apply()
        }



    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs10", Context.MODE_PRIVATE)
        val savedString = sharedPreferences?.getString("GAME_SCORE", null)
        try {
            highScore = savedString!!.toInt()
        } catch (e : Exception){
            highScore = 0
        }

        var tv = findViewById<TextView>(R.id.high_score)
        tv.text = savedString

    }
}