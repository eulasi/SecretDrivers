package com.example.secretdrivers.data.repository

import com.example.secretdrivers.data.db.sddriver.DriverEntity
import com.example.secretdrivers.data.model.driver.Driver
import kotlinx.coroutines.flow.Flow

interface SdRepository {
   suspend fun getAllDrivers(): Flow<List<DriverEntity>>

}