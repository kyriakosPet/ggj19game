package com.sliceofpizza.homegame.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.animation.LinearInterpolator
import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.infragments.InnerAFragment
import com.sliceofpizza.homegame.infragments.InnerBFragment
import com.sliceofpizza.homegame.infragments.InnerCFragment
import com.sliceofpizza.homegame.infragments.InnerDFragment
import kotlinx.android.synthetic.main.activity_in.*

class InActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in)

        createPager()
    }

    private fun createPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager, 4)
        pager.adapter = adapter
//        pager.scrollDuration = 500;
//        pager.interpolator = LinearInterpolator();
//        pager.isMediumScaled = false;
//        pager.maxPageScale = 1f
//        pager.minPageScale = 1f
//        pager.centerPageScaleOffset = 0f
//        pager.minPageScaleOffset = 0f
    }

    private class ViewPagerAdapter(fm: FragmentManager, val pages: Int) : FragmentPagerAdapter(fm) {

        private var a: Fragment? = null
        private var b: Fragment? = null
        private var c: Fragment? = null
        private var d: Fragment? = null

        override fun getItem(position: Int): Fragment? {
            return when(position){
                0 -> {
                    if (a == null) {
                        a = InnerAFragment()
                        a
                    }else {
                        a
                    }
                }
                1 -> {
                    if (b == null) {
                        b = InnerBFragment()
                        b
                    }else {
                        b
                    }
                }
                2 -> {
                    if (c == null) {
                        c = InnerCFragment()
                        c
                    }else {
                        c
                    }
                }
                else -> {
                    if (d == null) {
                        d = InnerDFragment()
                        d
                    }else {
                        d
                    }
                }
            }
        }

        override fun getCount(): Int {
            return pages
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "Tab " + (position + 1)
        }
    }
}
