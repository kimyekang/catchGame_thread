package com.pioneerapp.catchgame_thread

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class StartActivity : AppCompatActivity() {

    var highScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<TextView>(R.id.start_button).setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
        loadData()

        var score = intent.getIntExtra("SCORE", 0)

        if(highScore < score) {
            var tv = findViewById<TextView>(R.id.bestScore_tv)
            tv!!.text = highScore.toString()
            highScore = score
        }


    }



    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences?.getString("GAME_SCORE", null)

        highScore = savedString!!.toInt()

        var tv = findViewById<TextView>(R.id.bestScore_tv)
        tv!!.text = savedString



    }



}