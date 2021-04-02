package com.pavelhaleta.neurodiary.ui.main.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.pavelhaleta.neurodiary.R

class RecordTextDialog (var text: String): DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_record_text, container, false)

        view.findViewById<TextInputEditText>(R.id.et_text).setText(text)

        view.findViewById<FloatingActionButton>(R.id.b_save).setOnClickListener {
            listener.save(view.findViewById<TextInputEditText>(R.id.et_text).text.toString())
        }
        view.findViewById<FloatingActionButton>(R.id.b_cancel).setOnClickListener {
            listener.cancel()
        }
        view.findViewById<FloatingActionButton>(R.id.b_delete).setOnClickListener {
            listener.delete()
        }
        return view
    }

    interface NoticeListener {
        fun save(newText: String)
        fun delete()
        fun cancel()
    }

    private lateinit var listener: NoticeListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NoticeListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() +
                        " must implement NoticeDialogListener")
            )
        }
    }
}