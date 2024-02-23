package com.example.secretdrivers.data.model.driver


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "driver_table")
data class Driver(
    @PrimaryKey
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("name")
    var name: String? = "",
)
