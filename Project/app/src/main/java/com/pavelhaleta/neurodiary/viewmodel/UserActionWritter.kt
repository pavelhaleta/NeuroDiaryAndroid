package com.pavelhaleta.neurodiary.viewmodel

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.pavelhaleta.neurodiary.database.tables.Action
import com.pavelhaleta.neurodiary.database.tables.User
import com.pavelhaleta.neurodiary.database.tables.UserAction

interface UserActionWritter {

    fun writeActionToDB(db: SQLiteDatabase, user: User, action: Action){
        val ua = UserAction()
        ua.user = user
        ua.action = action
        Log.d("Debug", "all ok ${db.isOpen}")
        //ua.save(db)
    }
}