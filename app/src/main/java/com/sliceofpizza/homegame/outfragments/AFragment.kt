package com.sliceofpizza.homegame.outfragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot

import com.sliceofpizza.homegame.R
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

    fun setData(latestdataSnapshot: DataSnapshot?) {
        if(latestdataSnapshot?.hasChild("hasElectricity")!! && (latestdataSnapshot?.child("hasElectricity").value as Boolean)){
            reymabtn.visibility=View.GONE
        }else{
            reymabtn.visibility=View.VISIBLE
        }

    }



}
