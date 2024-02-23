package com.example.secretdrivers.data.model.driver


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "driver_table")
data class DriverModel(
    @PrimaryKey
    @SerializedName("drivers")
    val drivers: List<Driver> = emptyList(),
    @SerializedName("routes")
    val routes: List<Route> = emptyList()
)