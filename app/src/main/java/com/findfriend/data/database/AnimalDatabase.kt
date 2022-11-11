package com.findfriend.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [AnimalEntity::class, ImageEntity::class], exportSchema = false, version = 1)
abstract class AnimalDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao
    abstract fun imageDao(): ImageDao

    companion object {
        private var instance: AnimalDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AnimalDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context.applicationContext, AnimalDatabase::class.java,
                        "shelter_db")
                        .addCallback(roomCallback)
                        .build()
            }
            return instance!!
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }

}