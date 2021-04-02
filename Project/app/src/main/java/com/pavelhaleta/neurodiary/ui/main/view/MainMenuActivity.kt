package com.pavelhaleta.neurodiary.ui.main.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.ui.main.viewmodel.MainMenuVM

class MainMenuActivity : AppCompatActivity(R.layout.activity_main), MainMenuVM.VMListener {

    lateinit var viewModel: MainMenuVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //view model
        viewModel = MainMenuVM(this,this)
        //buttons
        findViewById<LinearLayoutCompat>(R.id.linear_add_image).setOnClickListener {
            viewModel.addImage()
        }
        findViewById<FloatingActionButton>(R.id.b_next_day).setOnClickListener {
            viewModel.nextDay()
        }
        findViewById<FloatingActionButton>(R.id.b_prev_day).setOnClickListener {
            viewModel.prevDay()
        }
        findViewById<FloatingActionButton>(R.id.b_mind_record).setOnClickListener {

        }
        findViewById<TextView>(R.id.b_mind_record).setOnClickListener {
            viewModel.editText()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
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