package com.pavelhaleta.neurodiary.database.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.basic.SQLColumn
import com.pavelhaleta.neurodiary.database.basic.SQLDataType
import com.pavelhaleta.neurodiary.database.basic.SQLTable

class MemoryContent : SQLTable("MemoryContent") {
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
            db.insert(this.tableName, null,contentData)
        } else {
            //update
            db.update(this.tableName, contentData, "id = $id", null)
        }
    }

}