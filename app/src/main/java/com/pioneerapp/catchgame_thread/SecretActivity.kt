package com.pioneerapp.catchgame_thread

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView

class SecretActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        findViewById<ImageView>(R.id.image).setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("IMAGE", "1")
            startActivity(intent)
        }
        findViewById<Button>(R.id.back_btn).setOnClickListener {
            finish()
        }
    }
}