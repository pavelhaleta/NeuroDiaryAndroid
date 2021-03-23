package com.pavelhaleta.neurodiary.database.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.core.database.getFloatOrNull
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import com.pavelhaleta.neurodiary.database.basic.SQLColumn
import com.pavelhaleta.neurodiary.database.basic.SQLDataType
import com.pavelhaleta.neurodiary.database.basic.SQLTable

class ContentParent : SQLTable("ContentParent"){
    //table fields
    private val _contentId = SQLColumn("content_id", SQLDataType.INT, this, -1)
    private val _parentId = SQLColumn("parent_id", SQLDataType.INT, this, -1)

    //load objects
    private var _content: MemoryContent = MemoryContent()
    private var _parent: MemoryContent = MemoryContent()

    //context objects
    var content: MemoryContent
        get() = _content
        set(value) {
            _content = value
            _contentId.value = _content.id
        }
    var parent: MemoryContent
        get() = _parent
        set(value) {
            _parent = value
            _parentId.value = _parent.id
        }

    override fun save(db: SQLiteDatabase) {
        super.save(db)
        val contentData = ContentValues()
        contentData.put(_contentId.toString(), _contentId.value as Int)
        contentData.put(_parentId.toString(), _contentId.value as Int)
        if (id == -1) {
            //create
            id = db.insert(this.tableName, null,contentData).toInt()
        } else {
            //update
            db.update(this.tableName, contentData, "id = $id", null)
        }
    }

    companion object{
        const val tableName = "ContentParent"

        fun toList(db: SQLiteDatabase, whereClause: String): ArrayList<ContentParent>?{
            val script = "SELECT id FROM $tableName $whereClause"
            val cursor = db.rawQuery(script, null)
            if (cursor.count == 0) {
                cursor.close()
                return null
            }
            val list = ArrayList<ContentParent>()
            cursor.moveToFirst()
            for (i in 0 until cursor.count){
                val element = ContentParent()
                element.load(db, cursor.getInt(0))
                list.add(element)
            }
            cursor.close()
            return list
        }
    }

}