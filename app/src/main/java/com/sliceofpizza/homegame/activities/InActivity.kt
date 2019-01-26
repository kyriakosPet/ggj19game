package com.sliceofpizza.homegame.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import com.antonyt.infiniteviewpager.InfinitePagerAdapter
import com.antonyt.infiniteviewpager.MinFragmentPagerAdapter
import com.google.firebase.database.*
import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.infragments.InnerAFragment
import com.sliceofpizza.homegame.infragments.InnerBFragment
import com.sliceofpizza.homegame.infragments.InnerCFragment
import com.sliceofpizza.homegame.infragments.InnerDFragment
import kotlinx.android.synthetic.main.activity_in.*
import kotlinx.android.synthetic.main.activity_main.*

class InActivity : AppCompatActivity() {

    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in)

        createPager()

        setupFirebaseDatabase()
    }

    private fun createPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager, 4)
        val wrappedMinAdapter = MinFragmentPagerAdapter(supportFragmentManager)
        wrappedMinAdapter.setAdapter(adapter)
        val wrappedAdapter = InfinitePagerAdapter(wrappedMinAdapter)

        inpager.adapter = wrappedAdapter
    }

    fun shootCannon() {
        myRef?.child("didShot")?.setValue("true")
    }

    private fun setupFirebaseDatabase() {
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("gamestatus")


        myRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //val value = dataSnapshot.getValue(String::class.java)
                valuetxt.text = dataSnapshot.child("message").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("eeeee", "Failed to read value.", error.toException())
            }
        })
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
