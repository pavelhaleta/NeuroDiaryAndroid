package com.pavelhaleta.neurodiary.database.tables

import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.basic.SQLColumn
import com.pavelhaleta.neurodiary.database.basic.SQLDataType
import com.pavelhaleta.neurodiary.database.basic.SQLTable
import com.pavelhaleta.neurodiary.model.MyDate
import com.pavelhaleta.neurodiary.model.MyTime

class UserAction : SQLTable("UserAction") {

    //table fields
    private val _date = SQLColumn("date", SQLDataType.TEXT, this, "01.01.2000")
    private val _time = SQLColumn("time", SQLDataType.TEXT, this, "00:00:00")
    private val _userId = SQLColumn("user_id", SQLDataType.INT, this, -1)
    private val _action = SQLColumn("action", SQLDataType.TEXT, this, "")
    private val _actionObject = SQLColumn("action_object", SQLDataType.TEXT, this, "")

    //load objects
    private var _user: User = User()

    //context fields
    var date: MyDate
        get() {
            return MyDate(_date.value as String)
        }
        set(value) {
            _date.value = value.toString()
        }

    var time: MyTime
        get() {
            return MyTime(_time.value as String)
        }
        set(value) {
            _time.value = value.toString()
        }
    var user: User
        get() = _user
        set(value) {
            _user = value
            _userId.value = _user.id
        }

    var action: String
        get() {
            return _action.value as String
        }
        set(value) {
            _action.value = value
        }
    var actionObject: String
        get() {
            return _actionObject.value as String
        }
        set(value) {
            _actionObject.value = value
        }

    override fun load(db: SQLiteDatabase, id: Int) {
        super.load(db, id)
    }

    override fun save(db: SQLiteDatabase) {
        super.save(db)
    }

    override fun delete(db: SQLiteDatabase) {
        super.delete(db)
    }
}