package com.sliceofpizza.homegame.infragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agilie.circularpicker.presenter.CircularPickerContract
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.maxLapForValves
import com.sliceofpizza.homegame.activities.InActivity
import kotlinx.android.synthetic.main.fragment_inner_d.*


class InnerDFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.sliceofpizza.homegame.R.layout.fragment_inner_d, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waste_button.setOnClickListener {
            takeWaste()
        }
        valve_button.setOnClickListener {
            (activity as? InActivity)?.openValveActivity("D")
        }
    }

    private fun takeWaste() {
        (activity as? InActivity)?.getWasteFromBin()
    }
}
