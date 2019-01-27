package com.sliceofpizza.homegame.outfragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.AlienHelper
import com.google.firebase.database.DataSnapshot

import com.sliceofpizza.homegame.R
import kotlinx.android.synthetic.main.fragment_d.*

class DFragment : AlienFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_d, container, false)
    }

    fun setData(latestdataSnapshot: DataSnapshot?) {

    }

    fun shoot() {
        laser.visibility = View.VISIBLE
        Handler().postDelayed({
            laser.visibility = View.GONE
        }, 1000)

        if (AlienHelper.isViewOverlapping(alien!!, hitarea)) {
            Log.d("aaaaa", "HITTTTTT")
            destroyAlien()
        } else {
            Log.d("aaaaa", "NOOOOT HITTTTTT")
        }

    }

}
