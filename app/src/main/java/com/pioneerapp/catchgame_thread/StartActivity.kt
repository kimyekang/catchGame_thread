package com.pioneerapp.catchgame_thread

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        findViewById<TextView>(R.id.start_button).setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("IMAGE", "0")
            startActivity(intent)
        }

    }
    // GestureDetecctor to detect long press
    private val gestureDetector = GestureDetector(object : GestureDetector.SimpleOnGestureListener() {
        override fun onLongPress(e: MotionEvent) {
            val intent = Intent(this@StartActivity, SecretActivity::class.java)
            startActivity(intent)
            // Toast to notify the Long Press
            Toast.makeText(applicationContext, "사진 눌러바!", Toast.LENGTH_SHORT).show()
        }
    })

    // onTouchEvent to confirm presence of Touch due to Long Press
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        return gestureDetector.onTouchEvent(event)
    }
}