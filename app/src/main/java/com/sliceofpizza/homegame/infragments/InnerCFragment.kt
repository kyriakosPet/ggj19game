package com.sliceofpizza.homegame.infragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.activities.InActivity

class InnerCFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inner_c, container, false)
    }

    private fun pressCannonButton() {
        (activity as? InActivity)?.shootCannon()
    }
}
