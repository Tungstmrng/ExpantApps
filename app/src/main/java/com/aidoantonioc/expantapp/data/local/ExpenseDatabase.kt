package com.aidoantonioc.expantapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aidoantonioc.expantapp.data.local.dao.CategoryDao
import com.aidoantonioc.expantapp.data.local.dao.ExpenseDao
import com.aidoantonioc.expantapp.data.local.entity.CategoryEntity
import com.aidoantonioc.expantapp.data.local.entity.ExpenseEntity

@Database(
    entities = [
        ExpenseEntity::class,
        CategoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    abstract fun categoryDao(): CategoryDao

    companion object {

        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        fun getDatabase(context: Context): ExpenseDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expantapps_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}