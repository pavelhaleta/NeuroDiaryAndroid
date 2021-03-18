package com.pavelhaleta.neurodiary.database.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.basic.SQLColumn
import com.pavelhaleta.neurodiary.database.basic.SQLDataType
import com.pavelhaleta.neurodiary.database.basic.SQLTable
import com.pavelhaleta.neurodiary.model.MyDate
import com.pavelhaleta.neurodiary.model.MyTime

class Record : SQLTable("Record"){
    //table fields
    private val _dateWrite = SQLColumn("date_write", SQLDataType.TEXT, this, "01.01.2000")
    private val _timeWrite = SQLColumn("time_write", SQLDataType.TEXT, this, "00:00:00")
    private val _date = SQLColumn("date", SQLDataType.TEXT, this, "01.01.2000")
    private val _userId = SQLColumn("user_id", SQLDataType.INT, this, -1)
    private val _title = SQLColumn("title", SQLDataType.TEXT, this, "")
    private val _textData = SQLColumn("text_data", SQLDataType.TEXT, this, "")

    //load objects
    private var _user: User = User()

    //context fields
    var dateWrite: MyDate
        get() {
            return MyDate(_dateWrite.value as String)
        }
        set(value) {
            _dateWrite.value = value.toString()
        }

    var timeWrite: MyTime
        get() {
            return MyTime(_timeWrite.value as String)
        }
        set(value) {
            _timeWrite.value = value.toString()
        }
    var date: MyDate
        get() {
            return MyDate(_date.value as String)
        }
        set(value) {
            _date.value = value.toString()
        }
    var user: User
        get() = _user
        set(value) {
            _user = value
            _userId.value = _user.id
        }
    var title: String
        get() {
            return _title.value as String
        }
        set(value) {
            _title.value = value
        }
    var textData: String
        get() {
            return _textData.value as String
        }
        set(value) {
            _textData.value = value
        }


    override fun save(db: SQLiteDatabase) {
        super.save(db)
        val contentData = ContentValues()
        contentData.put(_dateWrite.toString(), _dateWrite.value as String)
        contentData.put(_timeWrite.toString(), _timeWrite.value as String)
        if (id == -1) {
            //create
            id = db.insert(this.tableName, null,contentData).toInt()
        } else {
            //update
            db.update(this.tableName, contentData, "id = $id", null)
        }
    }
    companion object{
        val tableName = "Record"

        fun toList(db: SQLiteDatabase, whereClause: String): ArrayList<Record>?{
            val script = "SELECT id FROM ${tableName} $whereClause"
            val cursor = db.rawQuery(script, null)
            if (cursor.count == 0) {
                cursor.close()
                return null
            }
            val list = ArrayList<Record>()
            cursor.moveToFirst()
            for (i in 0 until cursor.count){
                val element = Record()
                element.load(db, cursor.getInt(0))
                list.add(element)
            }
            cursor.close()
            return list
        }
    }

}