package com.sliceofpizza.homegame.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log

import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.outfragments.*
import kotlinx.android.synthetic.main.activity_out.*
import com.antonyt.infiniteviewpager.MinFragmentPagerAdapter

import com.antonyt.infiniteviewpager.InfinitePagerAdapter
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants
import com.google.firebase.database.*

import java.util.*


class OutActivity : AppCompatActivity() {


    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null

    var latestdataSnapshot: DataSnapshot? = null


    var fa: AFragment? = null
    var fb: BFragment? = null
    var fc: CFragment? = null
    var fd: DFragment? = null
    var fe: EFragment? = null


    private fun changeValue() {
        myRef?.child("message")?.setValue("hello world " + System.currentTimeMillis())
    }


    private fun setupFirebaseDatabase() {
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("gamestatus")


        myRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                latestdataSnapshot = dataSnapshot

                if (dataSnapshot.hasChild("didShot") && dataSnapshot.child("didShot").value as Boolean) {
                    Log.d("eeeeee", "shooot")
                    myRef?.child("didShot")?.setValue(false)
                    fb?.shoot()
                    fc?.shoot()
                    fd?.shoot()
                }

                if (dataSnapshot.hasChild("health")) {
                    Log.d("eeeeee", "health " + dataSnapshot.child("health"))
                    progress_bar.progress = (dataSnapshot.child("health").value as Long).toInt()
                }

                fa?.setData(latestdataSnapshot)
                fb?.setData(latestdataSnapshot)
                fc?.setData(latestdataSnapshot)
                fd?.setData(latestdataSnapshot)
                fe?.setData(latestdataSnapshot)

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


    fun allienHitMe() {
        myRef?.child("health")?.setValue(progress_bar.progress - 10)
    }


    fun getData(): DataSnapshot? {
        return latestdataSnapshot
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out)

        setupPager()
        setupFirebaseDatabase()
        createAlienSpawnTimer()
    }


    private fun createAlienSpawnTimer() {

        val t = Timer()
        t.scheduleAtFixedRate(object : TimerTask() {

            override fun run() {
                if (latestdataSnapshot?.child("alienBz")?.value == null) {
                    fb?.spawnAlien()
                    if (fb != null) {
                        myRef?.child("alienBz")?.setValue(0)
                    }
                } else if (latestdataSnapshot?.child("alienCz")?.value == null) {
                    fc?.spawnAlien()
                    if (fc != null) {
                        myRef?.child("alienCz")?.setValue(0)
                    }
                } else if (latestdataSnapshot?.child("alienDz")?.value == null) {
                    fd?.spawnAlien()
                    if (fd != null) {
                        myRef?.child("alienDz")?.setValue(0)
                    }
                }
            }

        }, 100, Constants.alienspawnRepeat)
    }


    private fun setupPager() {

        val adapter = ViewPagerAdapter(supportFragmentManager, 5)

//        val wrappedMinAdapter = MinFragmentPagerAdapter(supportFragmentManager)
//        wrappedMinAdapter.setAdapter(adapter)
//        val wrappedAdapter = InfinitePagerAdapter(wrappedMinAdapter)

        oupager.offscreenPageLimit = 5
        oupager.adapter = adapter
    }

    fun nullVlue(vv: String) {
        myRef?.child(vv)?.setValue(null)
    }

    fun sentZ(vv: String, prog: Float) {
        myRef?.child(vv)?.setValue(prog)
    }


    inner class ViewPagerAdapter(fm: FragmentManager, val pages: Int) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            var fragment: Fragment? = null

            if (position == 0) {
                if (fa == null) {
                    fragment = AFragment()
                } else {
                    fragment = fa
                }
                fa = fragment as AFragment
            }
            if (position == 1) {
                if (fb == null) {
                    fragment = BFragment()
                } else {
                    fragment = fb
                }
                fb = fragment as BFragment
            }
            if (position == 2) {
                if (fc == null) {
                    fragment = CFragment()
                } else {
                    fragment = fc
                }
                fc = fragment as CFragment
            }
            if (position == 3) {
                if (fd == null) {
                    fragment = DFragment()
                } else {
                    fragment = fd
                }
                fd = fragment as DFragment
            }
            if (position == 4) {
                if (fe == null) {
                    fragment = EFragment()
                } else {
                    fragment = fe
                }
                fe = fragment as EFragment
            }

            Log.d("eeeee", "pos " + position)

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
