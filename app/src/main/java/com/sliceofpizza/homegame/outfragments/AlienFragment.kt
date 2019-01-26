package com.sliceofpizza.homegame.outfragments

import android.support.v4.app.Fragment
import android.view.View
import com.betheres.krsreporting.AppObject
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.AlienHelper
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Coordinate
import com.sliceofpizza.homegame.R
import kotlinx.android.synthetic.main.fragment_b.view.*

import java.util.ArrayList

open class AlienFragment : Fragment() {

    private  var waypoints: ArrayList<Coordinate> = ArrayList<Coordinate>()


    var startScale=0.5f
    var waypointNumer=0
    private var scaleStep =0f

    var alien: View? =null

    fun spawnAlien(){
        startScale=0.5f
        waypointNumer=0
        scaleStep =0f

        waypoints=AlienHelper.getAliensMovement()

        scaleStep = (1 - startScale ) / waypoints.size


        alien= view?.findViewById(R.id.alien)

        alien?.x=waypoints[0].x.toScreenWidth
        alien?.y=waypoints[0].y.toScreenHeight

        alien?.scaleX=startScale
        alien?.scaleY=startScale

        nextWayPoint(waypointNumer)
    }

    private fun nextWayPoint(waypointNumer: Int) {
        if(waypointNumer>=waypoints.size){
            alienHitMe()
            return
        }

        alien?.animate()!!.x(waypoints[waypointNumer].x.toScreenWidth).y(waypoints[waypointNumer].y.toScreenHeight).scaleY(startScale+ waypointNumer*scaleStep).scaleX(startScale+ waypointNumer*scaleStep).alpha(1f).setDuration(2000).withEndAction {
            this.waypointNumer++
        nextWayPoint(this.waypointNumer)}
    }

    private fun alienHitMe() {
        alien?.animate()!!.y(3000f).setDuration(1000).withEndAction {
            if(this is BFragment){

            }
            if(this is CFragment){

            }
            if(this is DFragment){

            }
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