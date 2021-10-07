package com.pioneerapp.catchgame_thread

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.media.Image
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet
import com.pioneerapp.catchgame_thread.databinding.ActivityGameBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class GameActivity : AppCompatActivity() {


    var playerX :Int = 50
    private lateinit var binding : ActivityGameBinding
    private var timer  = Timer()
    private val handler = Handler()
    private var actionFlag = true
    var appleX = 0
    var appleY = -30
    var apple2X = 0
    var apple2Y = -30
    var electroX = 0
    var electroY = -30
    var score = 0
    var heartCount = 4


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val display = windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size) // or getSize(size)
        val width = size.x
        var random : IntRange = (0..width - binding.scoreApple.width)
        appleX =  random.random()
        electroX =  random.random()
        apple2X = random.random()

        binding.scoreApple.x = -80.0f
        binding.scoreApple.y = -80.0f

        timer = timer(period = 100) {

            changePos()
            handler.post {
                if(heartCount == 3) {
                    binding.heart1.visibility = View.GONE
                } else if(heartCount == 2) {
                    binding.heart2.visibility = View.GONE
                } else if(heartCount == 1) {
                    binding.heart3.visibility = View.GONE
                } else if(heartCount == 0) {
                    binding.heart4.visibility = View.GONE
                    timer.cancel()
                }
                binding.scoreNumberTv.text = score.toString()
            }
        }


    }

    fun changePos() {

        hitCheck()

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size) // or getSize(size)
        val width = size.x
        val height = size.y
        var random : IntRange = (0..width - binding.scoreApple.width)

        //apple1
        appleY += 40
        if(appleY > height) {
            appleY = 0 - binding.scoreApple.height
            appleX =  random.random()
        }
        binding.scoreApple.y = appleY.toFloat()
        binding.scoreApple.x = appleX.toFloat()
        //apple2
        apple2Y += 40
        if(apple2Y > height) {
            apple2Y = 0 - binding.scoreApple2.height
            apple2X =  random.random()
        }
        binding.scoreApple2.y = apple2Y.toFloat()
        binding.scoreApple2.x = apple2X.toFloat()


        //electronic
        electroY += 40
        if(electroY > height) {
            electroY = 0 - binding.scoreElectronic.height
            electroX =  random.random()
        }
        binding.scoreElectronic.y = electroY.toFloat()
        binding.scoreElectronic.x = electroX.toFloat()

        //player
        if(actionFlag) {
            playerX += 50
        } else {
            playerX -= 50
        }

        if (playerX < 0) {
            playerX = 0
        }
        if (playerX > width - binding.player.width){
            playerX = width - binding.player.width
        }

        binding.player.x = playerX.toFloat()
       // binding.scoreNumberTv.text = score.toString()

    }

    fun hitCheck() {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size) // or getSize(size)
        val height = size.y
        val appleCenterX = appleX.toFloat() + binding.scoreApple.width / 2
        val appleCenterY = appleY.toFloat() + binding.scoreApple.height / 2
        val appleCenter2X = apple2X.toFloat() + binding.scoreApple2.width / 2
        val appleCenter2Y = apple2Y.toFloat() + binding.scoreApple2.height / 2
        val electroCenterX = electroX.toFloat() + binding.scoreElectronic.width / 2
        val electroCenterY = electroY.toFloat() + binding.scoreElectronic.height/ 2

        //apple1
        if(playerX <= appleCenterX && playerX + binding.player.width - 30 >= appleCenterX && height - binding.player.height <= appleCenterY && appleCenterY <= height - binding.player.height + 25  && 0 <= appleCenterY) {
            appleY = height + 1000
            score += 10
        }
        //apple2
        if(playerX <= appleCenter2X && playerX + binding.player.width  - 30>= appleCenter2X && height - binding.player.height <= appleCenter2Y  && appleCenter2Y <= height - binding.player.height + 25 && 0 <= appleCenter2Y) {
            apple2Y = height + 1000
            score += 10
        }

        //electronic
        if(playerX <= electroCenterX && playerX + binding.player.width >= electroCenterX && height - binding.player.height <= electroCenterY && electroCenterY <= height - binding.player.height + 25 && 0 <= electroCenterY) {
            electroY = height + 1000
            heartCount -= 1
            if(heartCount == 0) {
                val intent = Intent(this, StartActivity::class.java)
                intent.putExtra("SCORE", score)
                saveData()
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size) // or getSize(size)
        val width = size.x
        if(event?.action == MotionEvent.ACTION_DOWN) {
            actionFlag = event.x > width / 2
        }

        return super.onTouchEvent(event)
    }
    fun saveData() {
        val sharedPreferences  = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("GAME_SCORE", score.toString())
        editor?.apply()

    }

}