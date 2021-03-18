package com.pavelhaleta.neurodiary.view.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.pavelhaleta.neurodiary.R

class SignInFragment (): Fragment(R.layout.fragment_sign_in){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireView().findViewById<MaterialButton>(R.id.mb_sign_in).setOnClickListener {

        }
    }
}