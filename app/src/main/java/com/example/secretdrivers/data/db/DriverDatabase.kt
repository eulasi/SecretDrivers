package com.example.secretdrivers.data.db

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.secretdrivers.data.db.sddriver.DriverDao
import com.example.secretdrivers.data.db.sddriver.DriverEntity
import com.example.secretdrivers.data.db.sddriver.RouteDao
import com.example.secretdrivers.data.db.sddriver.RouteEntity
import com.example.secretdrivers.data.model.driver.Driver
import com.example.secretdrivers.data.model.driver.Route
import com.example.secretdrivers.util.DatabaseConverters

@Database(entities = [DriverEntity::class, RouteEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class DriverDatabase : RoomDatabase() {

    abstract fun driverDao(): DriverDao
    abstract fun routeDao(): RouteDao

    companion object {
        @Volatile
        private var Instance: DriverDatabase? = null

        fun getDatabase(context: Context) : DriverDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DriverDatabase::class.java, "driver_database")
                    .build()
                    .also {Instance = it }
            }
        }
    }
}