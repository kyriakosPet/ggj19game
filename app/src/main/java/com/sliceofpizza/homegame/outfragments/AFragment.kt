package com.sliceofpizza.homegame.outfragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot

import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.activities.InActivity
import com.sliceofpizza.homegame.activities.OutActivity
import com.sliceofpizza.homegame.activities.Puzzle
import kotlinx.android.synthetic.main.fragment_a.*

class AFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reymabtn.setOnClickListener {
            var i = Intent(activity, Puzzle::class.java)
            startActivity(i) }


    }
private var dY = 0.0f
    fun setData(latestdataSnapshot: DataSnapshot?) {
        if(latestdataSnapshot?.hasChild("hasElectricity")!! && (latestdataSnapshot?.child("hasElectricity").value as Boolean)){
            reymabtn.visibility=View.VISIBLE
        }

        if (latestdataSnapshot.hasChild("leverUp") && latestdataSnapshot.child("leverUp").value == true) {
            //can drag lever
            tenda.alpha = 1f
            tenda.animate().y(-1400f).setDuration(0).start()
            tenda.setOnTouchListener { v, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    dY = v.y - motionEvent.rawY
                }
                if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                    if (v.y >= 0f) {
                        (activity as? OutActivity)?.healthUp()
                    }else {
                        v.animate().y(motionEvent.rawY + dY).setDuration(0).start()
                    }
                }
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    resetTenda()
                }
                return@setOnTouchListener true
            }
        }else {
            resetTenda(true)
        }

        if (latestdataSnapshot!!.hasChild("valveA") && latestdataSnapshot.child("valveA").value as Boolean) {
            water.visibility = View.GONE
        } else {
            water.visibility = View.VISIBLE
        }

    }


    private fun resetTenda(clearTenda: Boolean = false) {
        activity.runOnUiThread {
            tenda.animate().translationY(-1400f).setDuration(200).withEndAction {
                if (clearTenda)
                tenda.alpha = 0f

            }.start()
        }
    }
}
