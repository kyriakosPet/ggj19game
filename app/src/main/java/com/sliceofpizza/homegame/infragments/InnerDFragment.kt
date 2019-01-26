package com.sliceofpizza.homegame.infragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.activities.InActivity
import kotlinx.android.synthetic.main.fragment_inner_d.*

class InnerDFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inner_d, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waste_button.setOnClickListener {
            takeWaste()
        }
    }

    private fun takeWaste() {
        (activity as? InActivity)?.getWasteFromBin()
    }
}
