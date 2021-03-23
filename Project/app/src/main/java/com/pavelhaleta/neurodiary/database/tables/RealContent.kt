package com.pavelhaleta.neurodiary.database.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.basic.SQLColumn
import com.pavelhaleta.neurodiary.database.basic.SQLDataType
import com.pavelhaleta.neurodiary.database.basic.SQLTable

class RealContent : SQLTable("RealContent"){
    //table fields
    private val _contentId = SQLColumn("content_id", SQLDataType.INT, this, -1)
    private val _recordId = SQLColumn("record_id", SQLDataType.INT, this, -1)

    //load objects
    private var _content: MemoryContent = MemoryContent()
    private var _record: Record = Record()

    //context objects
    var content: MemoryContent
        get() = _content
        set(value) {
            _content = value
            _contentId.value = _content.id
        }
    var record: Record
        get() = _record
        set(value) {
            _record = value
            _recordId.value = _record.id
        }


    override fun save(db: SQLiteDatabase) {
        super.save(db)
        val contentData = ContentValues()
        contentData.put(_contentId.toString(), _contentId.value as Int)
        contentData.put(_recordId.toString(), _recordId.value as Int)
        if (id == -1) {
            //create
            id = db.insert(this.tableName, null,contentData).toInt()
        } else {
            //update
            db.update(this.tableName, contentData, "id = $id", null)
        }
    }

    companion object{
        val tableName = "RealContent"

        fun toList(db: SQLiteDatabase, whereClause: String): ArrayList<RealContent>?{
            val script = "SELECT id FROM $tableName $whereClause"
            val cursor = db.rawQuery(script, null)
            if (cursor.count == 0) {
                cursor.close()
                return null
            }
            val list = ArrayList<RealContent>()
            cursor.moveToFirst()
            for (i in 0 until cursor.count){
                val element = RealContent()
                element.load(db, cursor.getInt(0))
                list.add(element)
            }
            cursor.close()
            return list
        }
    }
}