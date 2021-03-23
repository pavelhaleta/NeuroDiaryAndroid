package com.pavelhaleta.neurodiary.viewmodel.listeners

import com.pavelhaleta.neurodiary.viewmodel.other.ContactMessage

interface SignUpVMListener {
    fun allowCreate(allow: Boolean)
    fun errorPassword(contactMessage: ContactMessage?)
    fun errorName(contactMessage: ContactMessage?)
}