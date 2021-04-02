package com.pavelhaleta.neurodiary.database.entity

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.basic.SQLColumn
import com.pavelhaleta.neurodiary.database.basic.SQLDataType
import com.pavelhaleta.neurodiary.database.basic.SQLTable

class TableContentProperty : SQLTable("ContentProperty"){
    //table fields
    private val _contentId = SQLColumn("content_id", SQLDataType.INT, this, -1)
    private val _propertyId = SQLColumn("property_id", SQLDataType.INT, this, -1)

    //context objects
    var contentId: Int
        get() {
            return _contentId.value as Int
        }
        set(value) {
            _contentId.value = value
        }
    var propertyId: Int
        get() {
            return _propertyId.value as Int
        }
        set(value) {
            _propertyId.value = value
        }

    override fun save(db: SQLiteDatabase) {
        super.save(db)
        val contentData = ContentValues()
        contentData.put(_contentId.toString(), _contentId.value as Int)
        contentData.put(_propertyId.toString(), _propertyId.value as Int)
        if (id == -1) {
            //create
            id = db.insert(this.tableName, null,contentData).toInt()
        } else {
            //update
            db.update(this.tableName, contentData, "id = $id", null)
        }
    }
    companion object{
        const val tableName = "ContentProperty"

        fun toList(db: SQLiteDatabase, whereClause: String): ArrayList<TableContentProperty>?{
            val script = "SELECT id FROM $tableName $whereClause"
            val cursor = db.rawQuery(script, null)
            if (cursor.count == 0) {
                cursor.close()
                return null
            }
            val list = ArrayList<TableContentProperty>()
            cursor.moveToFirst()
            for (i in 0 until cursor.count){
                val element = TableContentProperty()
                element.load(db, cursor.getInt(0))
                list.add(element)
            }
            cursor.close()
            return list
        }
    }

}