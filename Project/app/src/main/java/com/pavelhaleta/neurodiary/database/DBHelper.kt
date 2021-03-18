package com.pavelhaleta.neurodiary.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.pavelhaleta.neurodiary.database.tables.*

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
        db.execSQL(ContentProperty().createTable())
        db.execSQL(ContentParent().createTable())
        db.execSQL(Goal().createTable())
        db.execSQL(MemoryContent().createTable())
        db.execSQL(PeriodData().createTable())
        db.execSQL(RealContent().createTable())
        db.execSQL(Record().createTable())
        db.execSQL(User().createTable())
        db.execSQL(UserAction().createTable())
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}