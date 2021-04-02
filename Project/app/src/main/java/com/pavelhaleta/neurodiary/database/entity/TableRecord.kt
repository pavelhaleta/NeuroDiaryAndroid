package com.pavelhaleta.neurodiary.database.entity

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.basic.SQLColumn
import com.pavelhaleta.neurodiary.database.basic.SQLDataType
import com.pavelhaleta.neurodiary.database.basic.SQLTable
import com.pavelhaleta.neurodiary.model.other.MyDate
import com.pavelhaleta.neurodiary.model.other.MyTime

class TableRecord : SQLTable("Record"){
    //table fields
    private val _dateWrite = SQLColumn("date_write", SQLDataType.TEXT, this, "01.01.2000")
    private val _timeWrite = SQLColumn("time_write", SQLDataType.TEXT, this, "00:00:00")
    private val _date = SQLColumn("date", SQLDataType.TEXT, this, "01.01.2000")
    private val _userId = SQLColumn("user_id", SQLDataType.INT, this, -1)
    private val _title = SQLColumn("title", SQLDataType.TEXT, this, "")
    private val _textData = SQLColumn("text_data", SQLDataType.TEXT, this, "")

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
    var userId: Int
        get() {
            return _userId.value as Int
        }
        set(value) {
            _userId.value = value
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

        fun toList(db: SQLiteDatabase, whereClause: String): ArrayList<TableRecord>?{
            val script = "SELECT id FROM $tableName $whereClause"
            val cursor = db.rawQuery(script, null)
            if (cursor.count == 0) {
                cursor.close()
                return null
            }
            val list = ArrayList<TableRecord>()
            cursor.moveToFirst()
            for (i in 0 until cursor.count){
                val element = TableRecord()
                element.load(db, cursor.getInt(0))
                list.add(element)
            }
            cursor.close()
            return list
        }
    }

}