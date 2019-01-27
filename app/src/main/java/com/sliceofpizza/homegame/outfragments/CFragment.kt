package com.sliceofpizza.homegame.outfragments


import android.os.Bundle
import android.os.Handler
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
import java.util.*

class CFragment : AlienFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.d("eeee", "c created")

        return inflater.inflate(R.layout.fragment_c, container, false)
    }


    private var latestData: DataSnapshot? = null

    private var waypoints: ArrayList<Coordinate> = ArrayList<Coordinate>()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        latestData = (activity as OutActivity).latestdataSnapshot
    }


    fun setData(latestdataSnapshot: DataSnapshot?) {
        if (latestdataSnapshot!!.hasChild("valveC") && latestdataSnapshot.child("valveC").value as Boolean) {
            water.visibility = View.GONE
        } else {
            water.visibility = View.VISIBLE
        }
    }

    fun shoot() {
        laser.visibility = View.VISIBLE
        Handler().postDelayed({
            laser.visibility = View.GONE
        }, 1000)

        var al = view?.findViewById<View>(R.id.alien)
        if (al != null) {
            if (AlienHelper.isViewOverlapping(al, hitarea)) {
                Log.d("aaaaa", "HITTTTTT")
                al?.clearAnimation()
                al?.visibility=View.GONE
                (activity as OutActivity).nullVlue("alienCz")

                explotion.visibility = View.VISIBLE
                explotion.x = al.x-300
                explotion.y = al.y-300
                Handler().postDelayed({
                    explotion.visibility = View.GONE
                }, 500)
                al?.x=5000f

            } else {
                Log.d("aaaaa", "NOOOOT HITTTTTT")
            }
        }
    }

}