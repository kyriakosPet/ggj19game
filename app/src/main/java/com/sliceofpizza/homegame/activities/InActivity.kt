package com.sliceofpizza.homegame.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sliceofpizza.homegame.R
import kotlinx.android.synthetic.main.activity_in.*

class InActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in)

        createPager()
    }

    private fun createPager() {
        
    }
}
