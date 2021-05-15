package com.example.doodlebluetask.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.example.doodlebluetask.model.AssetsResponse


@Database(
    entities = [AssetsResponse::class],
    version = 1
)

abstract class AssetDatabase : RoomDatabase() {

    abstract fun getAssetDao(): AssetDao
/*
    companion object {

        @Volatile
        private var INSTANCE: AssetDatabase? = null

        fun getDataseClient(context: Context): AssetDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AssetDatabase::class.java, "asset_db")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }*/

    companion object {
        // creating instance of article database
        @Volatile
        private var instance: AssetDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AssetDatabase::class.java,
            "asset_db.db"
        ).build()

    }
}