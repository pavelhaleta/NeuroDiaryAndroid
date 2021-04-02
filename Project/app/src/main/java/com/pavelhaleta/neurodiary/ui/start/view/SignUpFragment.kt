package com.pavelhaleta.neurodiary.ui.start.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.ui.start.viewmodel.SignUpVM

class SignUpFragment() : Fragment(R.layout.fragment_sign_up), SignUpVM.VMListener {

    lateinit var viewModel: SignUpVM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = SignUpVM(this.requireActivity() as StartActivity, this ,this.requireActivity() as StartActivity)
        view.findViewById<TextInputEditText>(R.id.et_password).addTextChangedListener {
            if (it != null){
                viewModel.setPassword(it.toString())
            }
        }
        view.findViewById<TextInputEditText>(R.id.et_name).addTextChangedListener {
            if (it != null){
                viewModel.setName(it.toString())
            }
        }
        view.findViewById<MaterialButton>(R.id.m_sign_up).setOnClickListener {
            viewModel.createAccount()
        }
        view.findViewById<MaterialTextView>(R.id.tv_sign_in).setOnClickListener {
            viewModel.changeToSignIn()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun allowCreate(allow: Boolean) {
        if (allow) {
            requireView().findViewById<MaterialButton>(R.id.m_sign_up).visibility = View.VISIBLE
        } else {
           requireView().findViewById<MaterialButton>(R.id.m_sign_up).visibility = View.GONE
        }

    }

    override fun errorPassword(error: String?) {
        if (error == null){
            requireView().findViewById<TextInputLayout>(R.id.text_layout_password).error = null
        }else{
            requireView().findViewById<TextInputLayout>(R.id.text_layout_password).error = error
        }

    }

    override fun errorName(error: String?) {
        if (error == null){
            requireView().findViewById<TextInputLayout>(R.id.text_layout_name).error = null
        }else{
            requireView().findViewById<TextInputLayout>(R.id.text_layout_name).error = error
        }

    }
}