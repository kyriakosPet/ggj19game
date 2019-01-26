package com.sliceofpizza.homegame.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
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
    }

    private class ViewPagerAdapter(fm: FragmentManager, val pages: Int) : FragmentPagerAdapter(fm) {

        private val fragments: Array<Fragment> = Array(4) {
            when(it){
                0 -> InnerAFragment()
                1 -> InnerBFragment()
                2 -> InnerCFragment()
                else -> InnerDFragment()
            }
        }

        override fun getItem(position: Int): Fragment? {
            return fragments[position]
        }

        override fun getCount(): Int {
            return pages
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "Tab " + (position + 1)
        }
    }
}
