package com.pavelhaleta.neurodiary.view.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import com.pavelhaleta.neurodiary.R

open class MainActivity(layout: Int) : AppCompatActivity(layout) {

    override fun onStart() {
        super.onStart()
        Log.d("ActivityAction", "$this on Start")
    }
    override fun onResume() {
        super.onResume()
        Log.d("ActivityAction", "$this on Resume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("ActivityAction", "$this on Pause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("ActivityAction", "$this on Stop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("ActivityAction", "$this on Destroy")
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}