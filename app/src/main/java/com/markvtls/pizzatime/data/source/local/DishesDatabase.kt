package com.markvtls.pizzatime.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database for storing API responses.
 */
@Database(entities = [DishEntity::class], version = 1, exportSchema = false)
abstract class DishesDatabase: RoomDatabase() {

    abstract fun dishDao(): DishDao

    companion object {
        /**
         * DishesDB instance used by all threads.
         */
        @Volatile
        private var INSTANCE: DishesDatabase? = null

        /**
         * Getting DishesDB instance.
         */
        fun getDatabase(context: Context): DishesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DishesDatabase::class.java,
                    "dishes_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}