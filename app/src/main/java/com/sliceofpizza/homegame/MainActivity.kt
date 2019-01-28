package com.sliceofpizza.homegame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import com.sliceofpizza.homegame.activities.InActivity
import com.sliceofpizza.homegame.activities.OutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupFirebaseDatabase()


        inbtn.setOnClickListener {
            var i =Intent(this, InActivity::class.java)
            startActivity(i)
        }

        outbtn.setOnClickListener {
            var i =Intent(this, OutActivity::class.java)
            startActivity(i)
        }


    }


    private fun resetValues() {
        myRef?.child("message")?.setValue("hello world " + System.currentTimeMillis())

        myRef?.child("alienBz")?.setValue(null)
        myRef?.child("alienCz")?.setValue(null)
        myRef?.child("alienDz")?.setValue(null)

        myRef?.child("valveA")?.setValue(true)
        myRef?.child("valveB")?.setValue(true)
        myRef?.child("valveC")?.setValue(true)
        myRef?.child("valveD")?.setValue(true)

        myRef?.child("health")?.setValue(100)
        myRef?.child("hasElectricity")?.setValue(true)
        myRef?.child("didShot")?.setValue(false)
        myRef?.child("leverUp")?.setValue(false)

    }


    private fun setupFirebaseDatabase() {
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("gamestatus")


        resetValues()

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

}
