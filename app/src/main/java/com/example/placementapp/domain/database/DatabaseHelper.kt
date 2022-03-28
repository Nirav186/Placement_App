package com.example.placementapp.domain.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.placementapp.data.dao.CompanyDao
import com.example.placementapp.data.dao.StudentDao
import com.example.placementapp.data.model.CompanyData
import com.example.placementapp.data.model.StudentData

@Database(
    entities = [CompanyData::class, StudentData::class],
    exportSchema = false,
    version = 1
)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun getCompanyDatabaseDao(): CompanyDao
    abstract fun getStudentDatabaseDao(): StudentDao

    companion object {
        private const val DB_NAME = "PlacementDatabase"

        @Volatile
        var instance: DatabaseHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            DatabaseHelper::class.java, DB_NAME
        ).build()
    }

}