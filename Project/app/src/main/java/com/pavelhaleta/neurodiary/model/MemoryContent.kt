package com.pavelhaleta.neurodiary.model

import android.database.sqlite.SQLiteDatabase

class MemoryContent (private var _name: String){
    private var _id: Int? = null
    private var _parents: ArrayList<MemoryContent>? = null
    private var _properties: ArrayList<MemoryContent>? = null



    constructor(name: String, parents: ArrayList<MemoryContent>?, properties: ArrayList<MemoryContent>? ): this(name){
        _name = name
        _parents = parents
        _properties = properties
    }

    fun setParent(parent: MemoryContent){

    }
    fun setProperty(property: MemoryContent){

    }

}