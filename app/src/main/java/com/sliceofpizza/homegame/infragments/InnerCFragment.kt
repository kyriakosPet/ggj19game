package com.sliceofpizza.homegame.infragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.activities.InActivity
import kotlinx.android.synthetic.main.fragment_inner_c.*

class InnerCFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inner_c, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cannon_button.setOnClickListener {
            pressCannonButton()
        }

        (activity as InActivity)
    }

    public fun updateRadar( position : Int , value : Float?){
       if ( value == null) {
           when (position) {
               0 -> {
                   stigmaLeft.visibility = View.GONE
                   stigmaLeft.translationY = 0f
                   stigmaLeft.translationX = 0f
               }
               1 -> {
                   stigmaMid.visibility = View.GONE
                   stigmaMid.translationX = 0f
                   stigmaMid.translationY = 0f
               }
               2 -> {
                   stigmaRight.visibility = View.GONE
                   stigmaRight.translationX = 0f
                   stigmaRight.translationY = 0f
               }
           }
       }else{
           when ( position ) {
               0 -> {
                   val x = (stigmaEnd.x - stigmaLeft.x) * value
                   val y = (stigmaEnd.y - stigmaEnd.y) * value
                   stigmaLeft.translationX = x
                   stigmaLeft.translationY = y

               }
               1 -> {
                   val y = (stigmaEnd.y - stigmaEnd.y) * value
                   stigmaLeft.translationY = y

               }
               2 -> {
                   val x = (stigmaLeft.x - stigmaEnd.x) * value
                   val y = (stigmaEnd.y - stigmaEnd.y) * value
                   stigmaLeft.translationX = x
                   stigmaLeft.translationY = y
               }

           }
       }


    }
    private fun pressCannonButton() {
        (activity as? InActivity)?.shootCannon()
    }
}
