package com.pavelhaleta.neurodiary.database.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.basic.SQLColumn
import com.pavelhaleta.neurodiary.database.basic.SQLDataType
import com.pavelhaleta.neurodiary.database.basic.SQLTable

class User : SQLTable("User"){
    //table fields
    private val _name = SQLColumn("name", SQLDataType.TEXT, this,"")
    private val _password = SQLColumn("password", SQLDataType.TEXT, this, "")
    private val _goalId = SQLColumn("goal_id", SQLDataType.INT, this, -1)
    private val _periodDataId = SQLColumn("period_data_id", SQLDataType.INT, this, -1)

    //load objects
    private var _goal: Goal = Goal()
    private var _periodData: PeriodData = PeriodData()

    //context fields
    var name: String
        get() {
            return _name.value as String
        }
        set(value) {
            _name.value = value
        }
    var password: String
        get() {
            return _password.value as String
        }
        set(value) {
            _password.value = value
        }
    var goal: Goal
        get() = _goal
        set(value) {
            _goal = value
            _goalId.value = _goal.id
        }
    var periodData: PeriodData
        get() = _periodData
        set(value) {
            _periodData = value
            _periodDataId.value = _periodData.id
        }

    override fun save(db: SQLiteDatabase) {
        super.save(db)
        val contentData = ContentValues()
        contentData.put(_name.toString(), _name.value as String)
        contentData.put(_password.toString(), _password.value as String)
        contentData.put(_goalId.toString(), _goalId.value as Int)
        contentData.put(_periodDataId.toString(), _periodDataId.value as Int)
        if (id == -1) {
            //create
            id = db.insert(this.tableName, null,contentData).toInt()
        } else {
            //update
            db.update(this.tableName, contentData, "id = $id", null)
        }
    }

    companion object{
        val tableName = "User"

        fun toList(db: SQLiteDatabase, whereClause: String): ArrayList<User>?{
            val script = "SELECT id FROM $tableName $whereClause"
            val cursor = db.rawQuery(script, null)
            if (cursor.count == 0) {
                cursor.close()
                return null
            }
            val list = ArrayList<User>()
            cursor.moveToFirst()
            for (i in 0 until cursor.count){
                val element = User()
                element.load(db, cursor.getInt(0))
                list.add(element)
            }
            cursor.close()
            return list
        }
    }

}