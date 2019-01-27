package com.sliceofpizza.homegame.outfragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.AlienHelper
import com.google.firebase.database.DataSnapshot

import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.activities.OutActivity
import kotlinx.android.synthetic.main.fragment_b.*

class BFragment : AlienFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    fun setData(latestdataSnapshot: DataSnapshot?) {

    }

    fun shoot() {
        laser.visibility = View.VISIBLE
        Handler().postDelayed({
            laser.visibility = View.GONE
        }, 1000)

        var al = view?.findViewById<View>(R.id.alien)
        if (al != null) {
            if (AlienHelper.isViewOverlapping(al, hitarea)) {
                Log.d("aaaaa", "HITTTTTT b")
                al?.clearAnimation()
                al?.visibility=View.GONE
                (activity as OutActivity).nullVlue("alienBz")
            } else {
                Log.d("aaaaa", "NOOOOT HITTTTTT")
            }
        }

    }


}
