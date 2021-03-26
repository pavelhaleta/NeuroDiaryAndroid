package com.pavelhaleta.neurodiary.view.start

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.database.tables.Action
import com.pavelhaleta.neurodiary.database.tables.User
import com.pavelhaleta.neurodiary.viewmodel.SignInVM
import com.pavelhaleta.neurodiary.viewmodel.SignUpVM
import com.pavelhaleta.neurodiary.viewmodel.listeners.SignInVMListener
import com.pavelhaleta.neurodiary.viewmodel.listeners.SignUpVMListener
import com.pavelhaleta.neurodiary.viewmodel.other.ContactMessage

class SignUpFragment() : Fragment(R.layout.fragment_sign_up), SignUpVMListener {

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