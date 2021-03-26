package com.pavelhaleta.neurodiary.view.start

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.viewmodel.SignInVM
import com.pavelhaleta.neurodiary.viewmodel.listeners.SignInVMListener
import com.pavelhaleta.neurodiary.viewmodel.other.ContactMessage

class SignInFragment() : Fragment(R.layout.fragment_sign_in), SignInVMListener {

    lateinit var viewModel: SignInVM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = SignInVM(this.requireActivity() as StartActivity, this, this.requireActivity() as StartActivity)
        view.findViewById<TextInputEditText>(R.id.et_password).addTextChangedListener {
            if (it != null) {
                viewModel.signIn(it.toString())
            }
        }
        view.findViewById<MaterialTextView>(R.id.tv_create_account).setOnClickListener {
            viewModel.changeToSignUp()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun errorPassword(error: String) {
        requireView().findViewById<TextInputLayout>(R.id.text_layout_password).error = error
    }
}