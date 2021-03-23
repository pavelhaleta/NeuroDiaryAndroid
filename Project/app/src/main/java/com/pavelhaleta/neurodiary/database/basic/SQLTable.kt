package com.pavelhaleta.neurodiary.database.basic

import android.database.sqlite.SQLiteDatabase
import androidx.core.database.getFloatOrNull
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import com.pavelhaleta.neurodiary.database.SQLOperation

open class SQLTable(var tableName: String) : SQLOperation {

    var columns = ArrayList<SQLColumn>()

    private var _id = SQLColumn("id", SQLDataType.ID, this, -1)

    var id: Int
        get() {
            return _id.value as Int
        }
        protected set(value) {
            _id.value = value
        }

    override fun load(db: SQLiteDatabase, id: Int) {
        super.load(db, id)
        var script = "SELECT "
        for (column in columns) {
            script += "${column.name} "
            script += if (columns.indexOf(column) != columns.size - 1) {
                ", "
            } else {
                " "
            }
        }
        script += "FROM $tableName WHERE id = $id; "
        val cursor = db.rawQuery(script, null)
        if (cursor.count == 0) {
            cursor.close()
            return
        }
        cursor.moveToFirst()
        for (column in columns){
            column.value = when(column.type){
                SQLDataType.ID -> cursor.getInt(cursor.getColumnIndex(column.toString()))
                SQLDataType.INT -> cursor.getInt(cursor.getColumnIndex(column.toString()))
                SQLDataType.INTNULL -> cursor.getIntOrNull(cursor.getColumnIndex(column.toString()))
                SQLDataType.REAL -> cursor.getFloat(cursor.getColumnIndex(column.toString()))
                SQLDataType.REALNULL -> cursor.getFloatOrNull(cursor.getColumnIndex(column.toString()))
                SQLDataType.TEXT -> cursor.getString(cursor.getColumnIndex(column.toString()))
                SQLDataType.TEXTNULL -> cursor.getStringOrNull(cursor.getColumnIndex(column.toString()))
                SQLDataType.BOOL -> cursor.getInt(cursor.getColumnIndex(column.toString()))
            }
        }
        cursor.close()
    }
    override fun loadFirstBy(db: SQLiteDatabase, whereClause: String) {
        var script = "SELECT "
        for (column in columns) {
            script += "${column.name} ${column.type}"
            script += if (columns.indexOf(column) != columns.size - 1) {
                ", "
            } else {
                " "
            }
        }
        script += "FROM $tableName WHERE $whereClause; "
        val cursor = db.rawQuery(script, null)
        if (cursor.count == 0) {
            cursor.close()
            return
        }
        cursor.moveToFirst()
        for (column in columns){
            column.value = when(column.type){
                SQLDataType.ID -> cursor.getInt(cursor.getColumnIndex(column.toString()))
                SQLDataType.INT -> cursor.getInt(cursor.getColumnIndex(column.toString()))
                SQLDataType.INTNULL -> cursor.getIntOrNull(cursor.getColumnIndex(column.toString()))
                SQLDataType.REAL -> cursor.getFloat(cursor.getColumnIndex(column.toString()))
                SQLDataType.REALNULL -> cursor.getFloatOrNull(cursor.getColumnIndex(column.toString()))
                SQLDataType.TEXT -> cursor.getString(cursor.getColumnIndex(column.toString()))
                SQLDataType.TEXTNULL -> cursor.getStringOrNull(cursor.getColumnIndex(column.toString()))
                SQLDataType.BOOL -> cursor.getInt(cursor.getColumnIndex(column.toString()))
            }
        }
        cursor.close()
    }


    override fun delete(db: SQLiteDatabase) {
        super.delete(db)
        db.delete(this.tableName, "id = $id", null)
    }

}