package com.pavelhaleta.neurodiary.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.basic.SQLTable

interface SQLOperation {
    fun load(db: SQLiteDatabase, id: Int){}
    fun loadFirstBy(db: SQLiteDatabase, whereClause: String){}
    fun save(db: SQLiteDatabase){}
    fun delete(db: SQLiteDatabase){}
}