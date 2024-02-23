package com.example.secretdrivers.util

import androidx.room.TypeConverter
import com.example.secretdrivers.data.db.sddriver.RouteEntity
import com.example.secretdrivers.data.model.driver.Route
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseConverters {
    @TypeConverter
    fun fromRouteList(routes: List<RouteEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<RouteEntity>>() {}.type
        return gson.toJson(routes, type)
    }

    @TypeConverter
    fun toRouteList(routeListString: String): List<RouteEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<RouteEntity>>() {}.type
        return gson.fromJson(routeListString, type)
    }
}