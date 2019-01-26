package com.sliceofpizza.homegame.outfragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot

import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.activities.OutActivity
import kotlinx.android.synthetic.main.fragment_c.*

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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        latestData=  (activity as OutActivity).latestdataSnapshot


        if(true){
            alien.animate().y(500f).withEndAction {
                (activity as OutActivity).allienHitMe()
            }
        }

    }

    fun setData(latestdataSnapshot: DataSnapshot?) {

    }


}
