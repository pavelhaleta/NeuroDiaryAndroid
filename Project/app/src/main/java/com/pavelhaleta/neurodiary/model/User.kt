package com.pavelhaleta.neurodiary.model

import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.entity.TableUser

class User {

    private var _id: Int? = null
    private var _name: String? = null

    fun getId(): Int? {
        return _id
    }

    fun load(db: SQLiteDatabase, password: String): Boolean {
        val userList = TableUser.toList(db, "")
        if(userList.isNullOrEmpty()){
            return false
        }
        val passwordSize = userList.minOf { it.password.length }
        if (password.length < passwordSize) {
            return false
        }
        val entity = userList.firstOrNull { it.password == password }
        return if (entity == null) {
            false
        } else {
            _id = entity.id
            _name = entity.name
            Action.SIGN_IN.write(db, this, "")
            true
        }
    }

    fun unload(db: SQLiteDatabase) {
        Action.EXIT.write(db, this, "")
        this._id = null
        this._name = null
    }

    fun create(db: SQLiteDatabase, nameInput: String, passwordInput: String): Boolean {
        val userList = TableUser.toList(db, "name = $nameInput")!!
        return if (!userList.isNullOrEmpty()){
            false
        }else{
            TableUser().apply {
                name = nameInput
                password = passwordInput
            }.also {
                it.save(db)
                Action.SIGN_UP.write(db, this, "")
            }
            true
        }
    }
}