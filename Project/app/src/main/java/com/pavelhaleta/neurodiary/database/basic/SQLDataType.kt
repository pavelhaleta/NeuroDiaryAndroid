package com.pavelhaleta.neurodiary.database.basic

enum class SQLDataType( var NAME: String) {
    ID(" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL"),
    TEXT(" TEXT NOT NULL"),
    TEXTNULL(" TEXT"),
    INT(" INTEGER NOT NULL"),
    INTNULL(" INTEGER"),
    BOOL(" INTEGER NOT NULL DEFAULT 0"),
    REAL(" REAL NOT NULL DEFAULT 0"),
    REALNULL(" REAL");

    override fun toString(): String {
        return NAME
    }
}