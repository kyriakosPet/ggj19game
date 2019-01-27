package com.sliceofpizza.homegame.infragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.activities.InActivity
import kotlinx.android.synthetic.main.fragment_inner_a.*

class InnerAFragment : Fragment()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inner_a, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        valve_1.setOnClickListener {
            (activity as? InActivity)?.openValveActivity("A")
        }

        valve_2.setOnClickListener {
            (activity as? InActivity)?.openValveActivity("B")
        }

        valve_3.setOnClickListener {
            (activity as? InActivity)?.openValveActivity("C")
        }

        valve_4.setOnClickListener {
            (activity as? InActivity)?.openValveActivity("D")
        }
    }
}
