package com.sliceofpizza.homegame.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agilie.circularpicker.presenter.CircularPickerContract
import com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers.Constants.Constants.maxLapForValves
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sliceofpizza.homegame.R
import kotlinx.android.synthetic.main.activity_valve.*

class ValveActivity : AppCompatActivity(), CircularPickerContract.Behavior.ValueChangedListener {

    var sector = ""
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valve)

        sector = intent.extras.getString("sector")

        setupFirebaseDatabase()

        valve.valueChangedListener = this
        valve.maxLapCount = maxLapForValves
        valve.maxValue = 100
        valve.currentValue = 0
        valve.centeredText = ""
    }

    private var values = ArrayList<Int>()

    override fun onValueChanged(value: Int) {
        values.add(value)
        values.distinct()
        if (values.size == 100) {
            myRef?.child("valve" + sector)?.setValue(true)
        }
        asset.rotation = value.toFloat() * 6.5f
    }

    private fun setupFirebaseDatabase() {
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("gamestatus")
    }
}
