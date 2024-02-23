package com.example.secretdrivers.data.remote

import com.example.secretdrivers.data.model.driver.DriverModel
import retrofit2.http.GET

interface ApiService {
    @GET(ApiDetails.END_POINT)
    suspend fun getDriverAndRoutes(): DriverModel
}