package com.example.secretdrivers.data.repository

import com.example.secretdrivers.data.db.sddriver.DriverDao
import com.example.secretdrivers.data.db.sddriver.DriverEntity
import com.example.secretdrivers.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SdRepositoryImpl @Inject constructor(
    private val driverDao: DriverDao,
    // private val apiService: ApiService
) : SdRepository {
    override suspend fun getAllDrivers(): Flow<List<DriverEntity>> {
        return driverDao.getAllDrivers()
    }
}

