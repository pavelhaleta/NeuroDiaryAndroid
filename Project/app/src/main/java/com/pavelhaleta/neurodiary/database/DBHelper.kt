package com.pavelhaleta.neurodiary.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.pavelhaleta.neurodiary.database.basic.SQLTable
import com.pavelhaleta.neurodiary.database.entity.*

class DBHelper(context: Context) : SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
) {
    companion object {
        var DATABASE_NAME = "nd.db"
        var DATABASE_VERSION = 1
    }

    var db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if (db == null) {
            return
        }
        createTable(db, TableContentProperty())
        createTable(db, TableContentParent())
        createTable(db, TableGoal())
        createTable(db, TableMemoryContent())
        createTable(db, TablePeriodData())
        createTable(db, TableRealContent())
        createTable(db, TableRecord())
        createTable(db, TableUser())
        createTable(db, TableUserAction())

        initialData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    private fun createTable(db: SQLiteDatabase, table: SQLTable){
        if (table.columns.isEmpty()) {
            return
        }
        var script = "CREATE TABLE ${table.tableName} ("
        for (column in table.columns) {
            script += "${column.name} ${column.type}"
            script += if (table.columns.indexOf(column) != table.columns.size - 1) {
                ", "
            } else {
                "); "
            }
        }
        db.execSQL(script)
        table.initialData(db)
    }

    private fun initialData(db: SQLiteDatabase){
        TableAction().apply {
            name = "SignIn"
        }.also { it.save(db) }
        TableAction().apply {
            name = "SignUn"
        }.also { it.save(db) }
        TableAction().apply {
            name = "Create"
        }.also { it.save(db) }
        TableAction().apply {
            name = "Edit"
        }.also { it.save(db) }
        TableAction().apply {
            name = "Delete"
        }.also { it.save(db) }
        TableAction().apply {
            name = "Open"
        }.also { it.save(db) }
    }
}