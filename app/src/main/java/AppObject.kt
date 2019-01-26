package com.betheres.krsreporting

import android.app.Application
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.WindowManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by KyriakosPetrolias on 12/22/2017.
 */
class AppObject : Application() {


    companion object {
        lateinit var instance: AppObject


        fun getScreenDimensions(): Point {
            val wm = instance.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
            var display = wm!!.defaultDisplay
            var size = Point()

            display.getSize(size)
//            screenWidth = size.x
//            screenHeight = size.y
            return size
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }


}