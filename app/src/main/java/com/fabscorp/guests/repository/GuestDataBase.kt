package com.fabscorp.guests.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fabscorp.guests.constants.DataBaseConstants
import com.fabscorp.guests.model.GuestModel

@Database (entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    //Singleton
    companion object {
        private lateinit var INSTANCE: GuestDataBase
        fun getDataBase(context: Context): GuestDataBase {
            synchronized(GuestDataBase::class.java) {// To prevent two or more threads from executing
                                                     //the same section below
                if (!::INSTANCE.isInitialized) {
                    INSTANCE =
                        Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                            .addMigrations(MIGRATION_1_2)
                            .allowMainThreadQueries()
                            .build()
                }
                return INSTANCE

            }
        }

        private val MIGRATION_1_2: Migration = object: Migration (1, 2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DELETE FROM Guest")
            }

        }
    }
}
