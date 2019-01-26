package com.sliceofpizza.homegame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupFirebaseDatabase()

        btn.setOnClickListener {
            changeValue()
        }
    }


    private fun changeValue() {
        myRef?.child("message")?.setValue("hello world " + System.currentTimeMillis())
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

}
