package com.pavelhaleta.neurodiary.database.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.basic.SQLColumn
import com.pavelhaleta.neurodiary.database.basic.SQLDataType
import com.pavelhaleta.neurodiary.database.basic.SQLTable

class Action : SQLTable("Action") {
    //table fields
    private val _name = SQLColumn("name", SQLDataType.TEXT, this, "")

    //context objects
    var name: String
        get() {
            return _name.value as String
        }
        set(value) {
            _name.value = value
        }


    override fun save(db: SQLiteDatabase) {
        super.save(db)
        val contentData = ContentValues()
        contentData.put(this.name, _name.value as String)
        if (id == -1) {
            //create
            id =  db.insert(this.tableName, null,contentData).toInt()
        } else {
            //update
            db.update(this.tableName, contentData, "id = $id", null)
        }
    }

    companion object{
        val tableName = "Goal"

        fun toList(db: SQLiteDatabase, whereClause: String): ArrayList<Action>?{
            val script = "SELECT id FROM ${tableName} $whereClause"
            val cursor = db.rawQuery(script, null)
            if (cursor.count == 0) {
                cursor.close()
                return null
            }
            val list = ArrayList<Action>()
            cursor.moveToFirst()
            for (i in 0 until cursor.count){
                val element = Action()
                element.load(db, cursor.getInt(0))
                list.add(element)
            }
            cursor.close()
            return list
        }
    }

}