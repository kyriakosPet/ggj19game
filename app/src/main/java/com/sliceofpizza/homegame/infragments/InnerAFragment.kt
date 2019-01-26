package com.sliceofpizza.homegame.infragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.activities.InActivity
import kotlinx.android.synthetic.main.fragment_inner_a.*

class InnerAFragment : Fragment()  {

    var isUp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_inner_a, container, false)

    }

    private var dY: Float = 0f

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
}
