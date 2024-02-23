package com.example.secretdrivers.data.module

import android.content.Context
import androidx.room.Room
import com.example.secretdrivers.data.db.DriverDatabase
import com.example.secretdrivers.data.db.sddriver.DriverDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideDriverDao(@ApplicationContext context: Context): DriverDao {
        return Room.databaseBuilder(
            context,
            DriverDatabase::class.java,
            "app_database"
        ).build().driverDao()
    }
}