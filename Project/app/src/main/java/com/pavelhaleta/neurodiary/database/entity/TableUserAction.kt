package com.pavelhaleta.neurodiary.database.entity

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.basic.SQLColumn
import com.pavelhaleta.neurodiary.database.basic.SQLDataType
import com.pavelhaleta.neurodiary.database.basic.SQLTable
import com.pavelhaleta.neurodiary.model.other.MyDate
import com.pavelhaleta.neurodiary.model.other.MyTime

class TableUserAction : SQLTable("UserAction") {

    //table fields
    private val _date = SQLColumn("date", SQLDataType.TEXT, this, "01.01.2000")
    private val _time = SQLColumn("time", SQLDataType.TEXT, this, "00:00:00")
    private val _userId = SQLColumn("user_id", SQLDataType.INT, this, -1)
    private val _actionId = SQLColumn("action_id", SQLDataType.INT, this, -1)
    private val _actionObject = SQLColumn("action_object", SQLDataType.TEXT, this, "")

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
    var userId: Int
        get() {
            return _userId.value as Int
        }
        set(value) {
            _userId.value = value
        }
    var actionId: Int
        get() {
            return _actionId.value as Int
        }
        set(value) {
            _actionId.value = value
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
        const val tableName = "UserAction"

        fun toList(db: SQLiteDatabase, whereClause: String): ArrayList<TableUserAction>?{
            val script = "SELECT id FROM $tableName $whereClause"
            val cursor = db.rawQuery(script, null)
            if (cursor.count == 0) {
                cursor.close()
                return null
            }
            val list = ArrayList<TableUserAction>()
            cursor.moveToFirst()
            for (i in 0 until cursor.count){
                val element = TableUserAction()
                element.load(db, cursor.getInt(0))
                list.add(element)
            }
            cursor.close()
            return list
        }
    }
}