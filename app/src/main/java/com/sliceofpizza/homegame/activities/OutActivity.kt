package com.sliceofpizza.homegame.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.outfragments.AFragment
import kotlinx.android.synthetic.main.activity_out.*

class OutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out)

        setupPager()
    }

    private fun setupPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager, 5)
        oupager.adapter = adapter

    }



    class ViewPagerAdapter(fm: FragmentManager, val pages: Int) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            var fragment: Fragment? = null

            if(position==0){
                fragment= AFragment()
            }

            return fragment
        }

        override fun getCount(): Int {
            return pages
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "Tab " + (position + 1)
        }
    }



}
