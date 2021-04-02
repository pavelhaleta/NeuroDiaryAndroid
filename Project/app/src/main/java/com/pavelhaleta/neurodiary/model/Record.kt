package com.pavelhaleta.neurodiary.model

import android.database.sqlite.SQLiteDatabase
import com.pavelhaleta.neurodiary.database.entity.TableRecord
import com.pavelhaleta.neurodiary.model.other.MyDate
import com.pavelhaleta.neurodiary.model.other.MyTime

class Record (time: MyTime, date: MyDate, db: SQLiteDatabase){

    private var _id: Int? = null
    private var _time: MyTime? = time
    private var _date: MyDate? = date
    private var _text: String? = null
    private var _contents: ArrayList<RealContent>? = null
    private var _images: ArrayList<RecordImage>? = null

    init {
        val entity = TableRecord()
        entity.loadFirstBy(db, "${entity.date} = $date")
        if (entity.isValid()){
            _id = entity.id
        }
    }


    fun addContent(content: RealContent,db: SQLiteDatabase ){
        if (_contents == null ){
            _contents = ArrayList()
        }
        _contents!!.add(content)
        save(db)
    }
    fun setText(text: String){

    }

    private fun save(db: SQLiteDatabase){

    }


}