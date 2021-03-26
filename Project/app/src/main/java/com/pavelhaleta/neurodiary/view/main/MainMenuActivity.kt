package com.pavelhaleta.neurodiary.view.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pavelhaleta.neurodiary.R

class MainMenuActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<FloatingActionButton>(R.id.b_prev_day).setOnFocusChangeListener { view, b ->
            Log.d("Debug", "focus $b")
        }
    }

    override fun onStart() {
        super.onStart()

    }
    override fun onResume() {
        super.onResume()

    }
    override fun onPause() {
        super.onPause()

    }
    override fun onStop() {
        super.onStop()

    }
    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}