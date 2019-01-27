package com.sliceofpizza.homegame.infragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        clearEnemy()
        (activity as InActivity)
    }

    private fun clearEnemy() {
        stigmaLeft1.alpha = 0f
        stigmaLeft2.alpha = 0f
        stigmaLeft3.alpha = 0f
        stigmaMid1.alpha = 0f
        stigmaMid2.alpha = 0f
        stigmaMid3.alpha = 0f
        stigmaRight1.alpha = 0f
        stigmaRight2.alpha = 0f
        stigmaRight3.alpha = 0f
    }

    fun updateRadar( position : Int , value : Double?){
        if (value == null) {
            clearEnemy()
            return
        }
        if(position == 0) {

            if (value <= 0.33) {
                stigmaLeft1.alpha = 1f
                stigmaLeft2.alpha = 0f
                stigmaLeft3.alpha = 0f
            }else if (value > 0.33 && value <= 0.66) {
                stigmaLeft2.alpha = 1f
                stigmaLeft3.alpha = 0f
                stigmaLeft1.alpha = 0f
            }else {
                stigmaLeft3.alpha = 1f
                stigmaLeft2.alpha = 0f
                stigmaLeft1.alpha = 0f
            }
        }
        if (position == 1) {
            if (value <= 0.33) {
                stigmaMid1.alpha = 1f
                stigmaMid2.alpha = 0f
                stigmaMid3.alpha = 0f
            }else if (value > 0.33 && value <= 0.66) {
                stigmaMid2.alpha = 1f
                stigmaMid1.alpha = 0f
                stigmaMid3.alpha = 0f
            }else {
                stigmaMid3.alpha = 1f
                stigmaMid1.alpha = 0f
                stigmaMid2.alpha = 0f
            }
        }
        else {
            if (value <= 0.33) {
                stigmaRight1.alpha = 1f
                stigmaRight2.alpha = 0f
                stigmaRight3.alpha = 0f
            }else if (value > 0.33 && value <= 0.66) {
                stigmaRight2.alpha = 1f
                stigmaRight1.alpha = 0f
                stigmaRight3.alpha = 0f
            }else {
                stigmaRight3.alpha = 1f
                stigmaRight1.alpha = 0f
                stigmaRight2.alpha = 0f
            }
        }
    }

    private fun pressCannonButton() {
        (activity as? InActivity)?.shootCannon()
    }
}
