package com.sliceofpizza.homegame.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.antonyt.infiniteviewpager.InfinitePagerAdapter
import com.antonyt.infiniteviewpager.MinFragmentPagerAdapter
import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.outfragments.*
import kotlinx.android.synthetic.main.activity_out.*


class OutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out)

        setupPager()
    }

    private fun setupPager() {


        val adapter = ViewPagerAdapter(supportFragmentManager, 5)


        val wrappedMinAdapter = MinFragmentPagerAdapter(supportFragmentManager)
        wrappedMinAdapter.setAdapter(adapter)
        val wrappedAdapter = InfinitePagerAdapter(wrappedMinAdapter)

        oupager.adapter = wrappedAdapter
    }



    class ViewPagerAdapter(fm: FragmentManager, val pages: Int) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            var fragment: Fragment? = null

            if(position==0){
                fragment= AFragment()
            }
            if(position==1){
                fragment = BFragment()
            }
            if(position==2){
                fragment = CFragment()
            }
            if(position==3){
                fragment = DFragment()
            }
            if(position==4){
                fragment = EFragment()
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
