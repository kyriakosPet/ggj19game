package com.sliceofpizza.homegame.outfragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betheres.krsreporting.AppObject
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.AlienHelper
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Coordinate
import com.google.firebase.database.DataSnapshot

import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.activities.OutActivity
import kotlinx.android.synthetic.main.fragment_c.*
import java.util.ArrayList

open class AlienFragment : Fragment() {

    private  var waypoints: ArrayList<Coordinate> = ArrayList<Coordinate>()


    var startScale=0.5f
    var waypointNumer=0
    private var scaleStep =0f

    fun spawnAlien(){
        startScale=0.5f
        waypointNumer=0
        scaleStep =0f

        waypoints=AlienHelper.getAliensMovement()

        scaleStep = (1 - startScale ) / waypoints.size

        alien.x=waypoints[0].x.toScreenWidth
        alien.y=waypoints[0].y.toScreenHeight

        alien.scaleX=startScale
        alien.scaleY=startScale

        nextWayPoint(waypointNumer)
    }

    private fun nextWayPoint(waypointNumer: Int) {
        if(waypointNumer>=waypoints.size){
            alienHitMe()
            return
        }

        alien.animate().x(waypoints[waypointNumer].x.toScreenWidth).y(waypoints[waypointNumer].y.toScreenHeight).scaleY(startScale+ waypointNumer*scaleStep).scaleX(startScale+ waypointNumer*scaleStep).alpha(1f).setDuration(2000).withEndAction {
            this.waypointNumer++
        nextWayPoint(this.waypointNumer)}
    }

    private fun alienHitMe() {
        alien.animate().y(3000f).setDuration(1000).withEndAction {

        }
    }


}

private val Int.toScreenWidth: Float
    get() {
        return this/100.toFloat()*AppObject.getScreenDimensions().x
    }
private val Int.toScreenHeight: Float
    get() {
        return this/100.toFloat()*AppObject.getScreenDimensions().y
    }