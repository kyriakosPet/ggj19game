package com.sliceofpizza.homegame.infragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.MainActivity
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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.view!!.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        (activity as InActivity).myRef!!.child("levelUp").setValue(true)
                        if ( !isUp ) {
                            isUp = true
                            lever.animate().translationYBy(-100f).setDuration(400).withEndAction {
                            }
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        if (isUp) {
                            isUp = false
                            lever.animate().translationYBy(100f).setDuration(400).withEndAction {
                                (activity as InActivity).myRef!!.child("levelUp").setValue(false)
                            }
                        }
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })
    }
}
