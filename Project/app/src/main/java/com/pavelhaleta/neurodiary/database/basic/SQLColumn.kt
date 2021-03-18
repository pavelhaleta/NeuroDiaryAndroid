package com.pavelhaleta.neurodiary.database.basic

class SQLColumn(
        val name: String,
        val type: SQLDataType,
        table: SQLTable,
        var value: Any?
) {

   init {
       table.columns.add(this)
   }

    override fun toString(): String {
        return name
    }
}