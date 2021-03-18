package com.pavelhaleta.neurodiary.database.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.basic.SQLColumn
import com.pavelhaleta.neurodiary.database.basic.SQLDataType
import com.pavelhaleta.neurodiary.database.basic.SQLTable

class ContentProperty : SQLTable("ContentField"){
    //table fields
    private val _contentId = SQLColumn("content_id", SQLDataType.INT, this, -1)
    private val _propertyId = SQLColumn("property_id", SQLDataType.INT, this, -1)

    //load objects
    private var _content: MemoryContent = MemoryContent()
    private var _property: MemoryContent = MemoryContent()


    //context objects
    var content: MemoryContent
        get() = _content
        set(value) {
            _content = value
            _contentId.value = _content.id
        }
    var property: MemoryContent
        get() = _property
        set(value) {
            _property = value
            _propertyId.value = _property.id
        }

    override fun save(db: SQLiteDatabase) {
        super.save(db)
        val contentData = ContentValues()
        contentData.put(_contentId.toString(), _contentId.value as Int)
        contentData.put(_propertyId.toString(), _propertyId.value as Int)
        if (id == -1) {
            //create
            db.insert(this.tableName, null,contentData)
        } else {
            //update
            db.update(this.tableName, contentData, "id = $id", null)
        }
    }

}