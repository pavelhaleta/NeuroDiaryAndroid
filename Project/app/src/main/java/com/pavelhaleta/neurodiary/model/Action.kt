package com.pavelhaleta.neurodiary.model

import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.application.NeuroApp
import com.pavelhaleta.neurodiary.database.entity.TableUserAction
import com.pavelhaleta.neurodiary.model.other.MyDate
import com.pavelhaleta.neurodiary.model.other.MyTime
import java.util.*

enum class Action(var id: Int , var value: String) {
    SIGN_IN(1, NeuroApp.getRes().getString(R.string.action_sign_in)),
    SIGN_UP(2, NeuroApp.getRes().getString(R.string.action_sign_up)),
    EXIT(3, NeuroApp.getRes().getString(R.string.action_exit)),
    CREATE_RECORD(4, NeuroApp.getRes().getString(R.string.action_create_record)),
    EDIT_RECORD(5, NeuroApp.getRes().getString(R.string.action_edit_record)),
    DELETE_RECORD(6, NeuroApp.getRes().getString(R.string.action_delete_record));

    fun write(db: SQLiteDatabase, user: User, abject: String): Boolean{
        if (user.getId() == null){
            return false
        }
        TableUserAction().apply {
            actionId = this@Action.id
            actionObject = abject
            date.current()
            time.current()
            userId = user.getId()!!
        }.also {
            it.save(db)
        }
        return true
    }
}