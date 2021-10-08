package com.pioneerapp.catchgame_thread

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.media.Image
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
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
    var electro2X = 0
    var electro2Y = -30
    var electro3X = 0
    var electro3Y = -30
    var electro4X = 0
    var electro4Y = -30
    var score = 0
    var heartCount = 4
    var character = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size) // or getSize(size)
        val width = size.x
        var random : IntRange = (0..width - binding.scoreApple.width)
        appleX =  random.random()
        apple2X = random.random()
        electroX =  random.random()
        electro2X =  random.random()
        electro3X =  random.random()
        electro4X =  random.random()

        if(intent.getStringExtra("IMAGE") == "1"){
            character = "1"
            binding.player.visibility = View.GONE
        } else if(intent.getStringExtra("IMAGE") == "0"){
            character = "0"
            binding.player2.visibility = View.GONE
        }


        timer = timer(period = 20) {
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
        //캐릭터설정
        var player = binding.player
        if(character == "0"){
            player = binding.player

        } else if (character == "1") {
            player = binding.player2
            binding.player.visibility = View.GONE
        }



        //apple1
        appleY += 12
        if(appleY > height) {
            appleY = 0 - binding.scoreApple.height
            appleX =  random.random()
        }
        binding.scoreApple.y = appleY.toFloat()
        binding.scoreApple.x = appleX.toFloat()

        //apple2
        apple2Y += 16
        if(apple2Y > height) {
            apple2Y = 0 - binding.scoreApple2.height
            apple2X =  random.random()
        }
        binding.scoreApple2.y = apple2Y.toFloat()
        binding.scoreApple2.x = apple2X.toFloat()

        //electronic
        electroY += 28
        if(electroY > height) {
            electroY = 0 - binding.scoreElectronic.height
            electroX =  random.random()
        }
        binding.scoreElectronic.y = electroY.toFloat()
        binding.scoreElectronic.x = electroX.toFloat()
        //electronic2
        electro2Y += 24
        if(electro2Y > height) {
            electro2Y = 0 - binding.scoreElectronic2.height
            electro2X =  random.random()
        }
        binding.scoreElectronic2.y = electro2Y.toFloat()
        binding.scoreElectronic2.x = electro2X.toFloat()
        //electronic3
        electro3Y += 32
        if(electro3Y > height) {
            electro3Y = 0 - binding.scoreElectronic3.height
            electro3X =  random.random()
        }
        binding.scoreElectronic3.y = electro3Y.toFloat()
        binding.scoreElectronic3.x = electro3X.toFloat()
        //electronic4
        electro4Y += 36
        if(electro4Y > height) {
            electro4Y = 0 - binding.scoreElectronic4.height
            electro4X =  random.random()
        }
        binding.scoreElectronic4.y = electro4Y.toFloat()
        binding.scoreElectronic4.x = electro4X.toFloat()

        //player
        if(actionFlag) {
            playerX += 12
        } else {
            playerX -= 12
        }

        if (playerX < 0) {
            playerX = 0
        }
        if (playerX > width - player.width){
            playerX = width - player.width
        }


        player.x = playerX.toFloat()
       // binding.scoreNumberTv.text = score.toString()

    }

    fun hitCheck() {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size) // or getSize(size)
        val height = size.y
        //캐릭터설정
        var player = binding.player
        if(character == "0"){
            player = binding.player
        } else if (character == "1") {
            player = binding.player2
        }
        val appleCenterX = appleX.toFloat() + binding.scoreApple.width / 2
        val appleCenterY = appleY.toFloat() + binding.scoreApple.height / 2
        val appleCenter2X = apple2X.toFloat() + binding.scoreApple2.width / 2
        val appleCenter2Y = apple2Y.toFloat() + binding.scoreApple2.height / 2
        val electroCenterX = electroX.toFloat() + binding.scoreElectronic.width / 2
        val electroCenterY = electroY.toFloat() + binding.scoreElectronic.height/ 2
        val electroCenter2X = electro2X.toFloat() + binding.scoreElectronic2.width / 2
        val electroCenter2Y = electro2Y.toFloat() + binding.scoreElectronic2.height/ 2
        val electroCenter3X = electro3X.toFloat() + binding.scoreElectronic3.width / 2
        val electroCenter3Y = electro3Y.toFloat() + binding.scoreElectronic3.height/ 2
        val electroCenter4X = electro4X.toFloat() + binding.scoreElectronic4.width / 2
        val electroCenter4Y = electro4Y.toFloat() + binding.scoreElectronic4.height/ 2

        //apple1
        if(playerX <= appleCenterX && playerX + player.width - 30 >= appleCenterX && height - player.height <= appleCenterY && appleCenterY <= height - player.height + 25  && 0 <= appleCenterY) {
            appleY = height + 1000
            score += 10
        }
        //apple2
        if(playerX <= appleCenter2X && playerX + player.width  - 30>= appleCenter2X && height - player.height <= appleCenter2Y  && appleCenter2Y <= height - player.height + 25 && 0 <= appleCenter2Y) {
            apple2Y = height + 1000
            score += 10
        }

        //electronic
        if(playerX <= electroCenterX && playerX + player.width >= electroCenterX && height - player.height <= electroCenterY && electroCenterY <= height - player.height + 25 && 0 <= electroCenterY) {
            electroY = height + 1000
            heartCount -= 1
        }
        //electronic2
        if(playerX <= electroCenter2X && playerX + player.width >= electroCenter2X && height - player.height <= electroCenter2Y && electroCenter2Y <= height - player.height + 25 && 0 <= electroCenter2Y) {
            electro2Y = height + 1000
            heartCount -= 1
        }
        //electronic3
        if(playerX <= electroCenter3X && playerX + player.width >= electroCenter3X && height - player.height <= electroCenter3Y && electroCenter3Y <= height - player.height + 25 && 0 <= electroCenter3Y) {
            electro3Y = height + 1000
            heartCount -= 1
        }
        //electronic4
        if(playerX <= electroCenter4X && playerX + player.width >= electroCenter4X && height - player.height <= electroCenter4Y && electroCenter4Y <= height - player.height + 25 && 0 <= electroCenter4Y) {
            electro4Y = height + 1000
            heartCount -= 1
        }
        //game over
        if(heartCount == 0) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("SCORE" , score.toString())
            startActivity(intent)
            finish()
        }
    }

    //일시정지 기능
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





}