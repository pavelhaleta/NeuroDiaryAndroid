package com.pavelhaleta.neurodiary.database

import android.database.sqlite.SQLiteDatabase

interface SQLOperation {
    fun load(db: SQLiteDatabase, id: Int){}
    fun save(db: SQLiteDatabase){}
    fun delete(db: SQLiteDatabase){}
}