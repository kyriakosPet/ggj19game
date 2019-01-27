package com.sliceofpizza.homegame.activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sliceofpizza.homegame.R
import kotlinx.android.synthetic.main.activity_puzzle.*
import kotlin.random.Random

class Puzzle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puzzle)
        scrumble()
        puzzle1.setOnClickListener {
            rotateAndCheck(puzzle1)
        }
        puzzle2.setOnClickListener {
            rotateAndCheck(puzzle2)
        }
        puzzle3.setOnClickListener {
            rotateAndCheck(puzzle3)
        }
        puzzle4.setOnClickListener {
            rotateAndCheck(puzzle4)
        }
        puzzle5.setOnClickListener {
            rotateAndCheck(puzzle5)
        }
        puzzle6.setOnClickListener {
            rotateAndCheck(puzzle6)
        }
        puzzle7.setOnClickListener {
            rotateAndCheck(puzzle7)
        }
        puzzle8.setOnClickListener {
            rotateAndCheck(puzzle8)
        }
        puzzle9.setOnClickListener {
            rotateAndCheck(puzzle9)
        }

    }

    fun checkForWin(){
//        if ( puzzle1.rotation == 0f &&
//                puzzle2.rotation == 0f &&
//                puzzle3.rotation == 0f &&
//                puzzle4.rotation == 0f &&
//                puzzle5.rotation == 0f &&
//                puzzle6.rotation == 0f &&
//                puzzle7.rotation == 0f &&
//                puzzle8.rotation == 0f &&
//                puzzle9.rotation == 0f
//                ){
//            winAndClose()
//        }
        Log.d("Rotation : " , "===========================")

        Log.d("Rotation : " , " puzzle  1 " + puzzle1.rotation)
        Log.d("Rotation : " , " puzzle  2 " + puzzle2.rotation)
        Log.d("Rotation : " , " puzzle 3 " + puzzle3.rotation)
        Log.d("Rotation : " , " puzzle  4 " + puzzle4.rotation)
        Log.d("Rotation : " , " puzzle  5 " + puzzle5.rotation)
        Log.d("Rotation : " , " puzzle  6 " + puzzle6.rotation)
        Log.d("Rotation : " , " puzzle  7 " + puzzle7.rotation)
        Log.d("Rotation : " , " puzzle  8 " + puzzle8.rotation)
        Log.d("Rotation : " , " puzzle  9 "  + puzzle9.rotation)
        Log.d("Rotation : " , "===========================")

        if ( puzzle1.rotation == 0f &&
                puzzle2.rotation == 90f &&
                puzzle3.rotation == 0f &&
                (puzzle4.rotation == 90f || puzzle4.rotation == 270f) &&
                (puzzle5.rotation == 90f || puzzle5.rotation == 270f) &&
                (puzzle6.rotation == 90f || puzzle6.rotation == 270f) &&
                puzzle7.rotation == 180f &&
                puzzle8.rotation == 270f &&
                puzzle9.rotation == 180f
        ){
            winAndClose()
        }

    }

    private fun winAndClose() {

        var database = FirebaseDatabase.getInstance()
        var myRef = database!!.getReference("gamestatus")
        myRef?.child("hasElectricity")?.setValue(true)
        finish()
    }

    fun rotateAndCheck(v : View) {
        v.animate().rotationBy(90f).setDuration(500).withEndAction {
            Log.d("eeeee","rotation"  + v.rotation)
            if ( v.rotation >= 360f)
                v.rotation = 0f
            if ( v.rotation == 0f) {
                v.setBackgroundColor(ContextCompat.getColor(this,R.color.red))
            }
            checkForWin()
        }
    }
    fun scrumble(){
        puzzle1.rotation = giveRandomRotation()
        puzzle2.rotation = giveRandomRotation()
        puzzle3.rotation = giveRandomRotation()
        puzzle4.rotation = giveRandomRotation()
        puzzle5.rotation = giveRandomRotation()
        puzzle6.rotation = giveRandomRotation()
        puzzle7.rotation = giveRandomRotation()
        puzzle8.rotation = giveRandomRotation()
        puzzle9.rotation = giveRandomRotation()

    }

    fun giveRandomRotation() : Float {
        val rotation = Random.nextInt(0,3)
        when(rotation){
            0-> return 0f
            1-> return 90f
            2-> return 180f
            3-> return 270f
        }
        return 360f
    }
}
