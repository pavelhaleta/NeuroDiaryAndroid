package com.pavelhaleta.neurodiary.database.tables

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


    override fun load(db: SQLiteDatabase, id: Int) {
        super.load(db, id)
        val cursor = db.rawQuery("SELECT $_name, $_password, $_goalId, $_periodDataId FROM ${this.tableName} WHERE id = $id;", null)
        if (cursor.count == 0) {
            cursor.close()
            return
        }
        cursor.moveToFirst()

        this.id = id
        name = cursor.getString(cursor.getColumnIndex(_name.toString()))
        password = cursor.getString(cursor.getColumnIndex(_password.toString()))
        goal = Goal().also { it.load(db, cursor.getInt(cursor.getColumnIndex(_goalId.toString()))) }
        periodData = PeriodData().also { it.load(db, cursor.getInt(cursor.getColumnIndex(_periodDataId.toString()))) }
        cursor.close()
    }

    override fun save(db: SQLiteDatabase) {
        super.save(db)
    }

    override fun delete(db: SQLiteDatabase) {
        super.delete(db)
    }

}