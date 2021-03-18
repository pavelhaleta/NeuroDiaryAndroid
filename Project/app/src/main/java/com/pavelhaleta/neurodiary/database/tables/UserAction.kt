package com.pavelhaleta.neurodiary.database.tables

import android.content.ContentValues
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
    private val _actionId = SQLColumn("action_id", SQLDataType.INT, this, -1)
    private val _actionObject = SQLColumn("action_object", SQLDataType.TEXT, this, "")

    //load objects
    private var _user: User = User()
    private var _action: Action = Action()

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

    var action: Action
        get() = _action
        set(value) {
            _action = value
            _actionId.value = _action.id
        }
    var actionObject: String
        get() {
            return _actionObject.value as String
        }
        set(value) {
            _actionObject.value = value
        }

    override fun save(db: SQLiteDatabase) {
        super.save(db)
        val contentData = ContentValues()
        contentData.put(_date.toString(), _date.value as String)
        contentData.put(_time.toString(), _time.value as String)
        contentData.put(_userId.toString(), _userId.value as Int)
        contentData.put(_actionId.toString(), _actionId.value as Int)
        contentData.put(_actionObject.toString(), _actionObject.value as String)
        if (id == -1) {
            //create
            id = db.insert(this.tableName, null,contentData).toInt()
        } else {
            //update
            db.update(this.tableName, contentData, "id = $id", null)
        }
    }


    companion object{
        val tableName = "UserAction"

        fun toList(db: SQLiteDatabase, whereClause: String): ArrayList<UserAction>?{
            val script = "SELECT id FROM ${tableName} $whereClause"
            val cursor = db.rawQuery(script, null)
            if (cursor.count == 0) {
                cursor.close()
                return null
            }
            val list = ArrayList<UserAction>()
            cursor.moveToFirst()
            for (i in 0 until cursor.count){
                val element = UserAction()
                element.load(db, cursor.getInt(0))
                list.add(element)
            }
            cursor.close()
            return list
        }
    }
}