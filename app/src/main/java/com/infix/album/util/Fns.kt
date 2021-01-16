package com.infix.album.util

import android.graphics.Color
import android.util.Log
import android.widget.ImageView
import com.amulyakhare.textdrawable.TextDrawable
import java.util.*



fun setRandomImage(name: String, image: ImageView,radius:Int) {
    val randomNumber = Random()
    val color = Color.argb(255, randomNumber.nextInt(256), randomNumber.nextInt(256), randomNumber.nextInt(256))
    val drawable = TextDrawable.builder().buildRoundRect(name[0].toString().toUpperCase(), color, radius)
    image.setImageDrawable(drawable)
}

fun log(message:String){
    Log.d("AppLogs", message)
}