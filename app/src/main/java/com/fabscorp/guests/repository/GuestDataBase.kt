package com.fabscorp.guests.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.fabscorp.guests.constants.DataBaseConstants

class GuestDataBase(context: Context,) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Data Base Create
        db.execSQL("CREATE TABLE" + DataBaseConstants.GUEST.TABLE_NAME + "(" +
                DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement," +
                DataBaseConstants.GUEST.COLUMNS.NAME + " text," +
                DataBaseConstants.GUEST.COLUMNS.PRESENCE +" integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}