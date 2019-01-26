package com.sliceofpizza.homegame.outfragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.AlienHelper
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Coordinate
import com.google.firebase.database.DataSnapshot

import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.activities.OutActivity
import kotlinx.android.synthetic.main.fragment_c.*
import java.util.ArrayList

class CFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.d("eeee","c created")

        return inflater.inflate(R.layout.fragment_c, container, false)
    }


    private var latestData: DataSnapshot? = null

    private  var waypoints: ArrayList<Coordinate> = ArrayList<Coordinate>()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        latestData=  (activity as OutActivity).latestdataSnapshot


        if(true){
            //    (activity as OutActivity).allienHitMe()
            spawnAlien()
        }

    }

var waypointNumer=0
    fun spawnAlien(){
        waypoints=AlienHelper.getAliensMovement()

        alien.x=waypoints[0].x.toFloat()
        alien.y=waypoints[0].y.toFloat()
        nextWayPoint(waypointNumer)

    }

    private fun nextWayPoint(waypointNumer: Int) {
        if(waypointNumer>=waypoints.size){
            alienHitMe()
            return
        }
        Log.d("eeee","pos x "+waypoints[waypointNumer].x + "pos y "+waypoints[waypointNumer].y)

        alien.animate().x(waypoints[waypointNumer].x.toFloat()).y(waypoints[waypointNumer].y.toFloat()).setDuration(1000).withEndAction {
            this.waypointNumer++
        nextWayPoint(this.waypointNumer)}
    }

    private fun alienHitMe() {

    }

    fun setData(latestdataSnapshot: DataSnapshot?) {

    }


}
