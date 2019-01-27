package com.sliceofpizza.homegame.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.antonyt.infiniteviewpager.InfinitePagerAdapter
import com.antonyt.infiniteviewpager.MinFragmentPagerAdapter
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.blackOutChance
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.blackOutRepeat
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.hasBlackOut
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.hasWaste
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.wasteAmount
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.wasteFillAmount
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.wasteMax
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.wasteMin
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.wasteReduction
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.wasteRepeat
import com.google.firebase.database.*
import com.sliceofpizza.homegame.R
import com.sliceofpizza.homegame.infragments.InnerAFragment
import com.sliceofpizza.homegame.infragments.InnerBFragment
import com.sliceofpizza.homegame.infragments.InnerCFragment
import com.sliceofpizza.homegame.infragments.InnerDFragment
import kotlinx.android.synthetic.main.activity_in.*
import java.util.*
import kotlin.random.Random

class InActivity : AppCompatActivity() {

    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var latestdataSnapshot: DataSnapshot?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in)

        createPager()

        setupFirebaseDatabase()

        createWasteTimer()

        createBlackOutTimer()
    }

    private fun createPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager, 4)
        val wrappedMinAdapter = MinFragmentPagerAdapter(supportFragmentManager)
        wrappedMinAdapter.setAdapter(adapter)
        val wrappedAdapter = InfinitePagerAdapter(wrappedMinAdapter)

        inpager.adapter = wrappedAdapter
    }

    fun shootCannon() {
        myRef?.child("didShot")?.setValue(true)
    }

    private var wateTimer: Timer = Timer()

    private fun createWasteTimer() {
        waste_progress_bar.progress = 0
        wateTimer.scheduleAtFixedRate(object : TimerTask() {

            override fun run() {
                wasteAmount += wasteFillAmount
                setWasteProgress()
            }

        }, 1000, wasteRepeat)
    }

    fun getWasteFromBin() {
        if (!hasWaste) {
            hasWaste = true
            if (wasteAmount > wasteReduction) {
                wasteAmount -= wasteReduction
            }else {
                wasteAmount = wasteMin
            }
            setWasteProgress()
        }
    }

    private fun setWasteProgress() {
        waste_progress_bar.progress = wasteAmount.toInt()
        if (wasteAmount >= wasteMax) {
            wateTimer.cancel()
            runOnUiThread {
//                val alertDialog = AlertDialog.Builder(this).create() //Read Update
//                alertDialog.setTitle("Game Over")
//                alertDialog.setMessage("YOU DIED")
//
//                alertDialog.setButton("Continue") { _, _ ->
//                    finish()
//                }
//
//                alertDialog.show()
            }
        }
    }

    fun emptyWaste() {
        if (hasWaste) {
            hasWaste = false
        }
    }

    fun openValveActivity(inSector: String) {
        val i = Intent(this, ValveActivity::class.java)
        i.putExtra("sector", inSector)
        startActivity(i)
    }

    fun sendLeverUp() {
        myRef?.child("leverUp")?.setValue(true)
    }

    fun sendLeverDown() {
        myRef?.child("leverUp")?.setValue(false)
    }

    private fun createBlackOutTimer() {
        val t = Timer()
        t.scheduleAtFixedRate(object : TimerTask() {

            override fun run() {
                if (hasBlackOut) {
                    return
                }
                val item = Random.nextInt(0, 20)
                if (item == blackOutChance){
                    hasBlackOut = true
                    myRef?.child("hasElectricity")?.setValue(false)
                    runOnUiThread { black_out_view.animate().alpha(0.9f).setDuration(300).start() }
                }
            }

        }, 1000, blackOutRepeat)
    }

    private fun setupFirebaseDatabase() {
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("gamestatus")


        myRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                latestdataSnapshot=dataSnapshot

                if(dataSnapshot.hasChild("health") ){
                    Log.d("eeeeee", "health " + dataSnapshot.child("health"))
                    progress_bar.progress= (dataSnapshot.child("health").value as Long).toInt()
                }

                if(dataSnapshot.child("hasElectricity").value == true) {
                    hasBlackOut = false
                    runOnUiThread {
                        black_out_view.animate().alpha(0f).setDuration(300).start()
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {

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
