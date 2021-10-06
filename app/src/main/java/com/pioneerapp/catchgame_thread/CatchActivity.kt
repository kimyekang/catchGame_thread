package com.pioneerapp.catchgame_thread

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CatchActivity(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val p = Paint()

    var scrh :Int = 0
    var scrw :Int = 0
    var d : Int = 0


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        scrw = w
        scrh = h
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        p.color = Color.parseColor("#ff0000")
        p.textSize = (scrh/16).toFloat()

        val i = BitmapFactory.decodeResource(resources, R.drawable.catwithbasket)
        Bitmap.createScaledBitmap(i, scrw / 8, scrh / 4, true)


        canvas?.drawBitmap(i, d, scrh - scrh / 4, null)
        canvas?.drawText("sw $scrw sh", 0F, (scrh/16).toFloat(), p)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event!!.action == MotionEvent.ACTION_DOWN) {
            if(event.x > scrw / 2) {
                d+=10
            } else {
                d-=10
            }

        }

        return super.onTouchEvent(event)
    }
}