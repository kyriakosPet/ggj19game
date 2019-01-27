package com.sliceofpizza.homegame.infragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.agilie.circularpicker.presenter.CircularPickerContract
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.maxLapForValves
import com.sliceofpizza.homegame.activities.InActivity
import kotlinx.android.synthetic.main.fragment_inner_d.*


class InnerDFragment : Fragment() {

    private var dY: Float = 0f

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

        lever.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                dY = v.y - motionEvent.rawY
            }
            if (motionEvent.action == MotionEvent.ACTION_MOVE && motionEvent.rawY + dY < v.y) {
                if (motionEvent.rawY + dY > 500) {
                    v.animate().y(motionEvent.rawY + dY).setDuration(0).start()
                } else {
                    (activity as? InActivity)?.sendLeverUp()
                }
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                activity.runOnUiThread {
                    v.animate().translationY(0f).setDuration(200).withEndAction {
                        (activity as? InActivity)?.sendLeverDown()
                    }.start()
                }
            }
            return@setOnTouchListener true
        }
    }

    private fun takeWaste() {
        (activity as? InActivity)?.getWasteFromBin()
    }
}
