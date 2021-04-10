package com.araguas.pac_mp8

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper (context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper (context,name,factory,version){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table empleados(num text primary key, nombre text, correo text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


}