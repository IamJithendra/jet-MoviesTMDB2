package com.karlis.moviestmdb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, "MOVIESDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE MOVIES(MOVIEID INTEGER PRIMARY KEY AUTOINCREMENT, ID text, MOV_LANG TEXT, MOV_POP TEXT, MOV_PP TEXT, MOV_RD TEXT, MOV_TITLE TEXT, MOV_VOTE TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}