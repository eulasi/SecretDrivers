package com.example.secretdrivers.data.db.sddriver

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverDao {

    @Query("SELECT * FROM driver_table")
    fun getAllDrivers(): Flow<List<DriverEntity>>

    @Query("SELECT * FROM driver_table WHERE name = :name AND id = :id")
    fun getById(name: String, id: String): Flow<DriverEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(driver: DriverEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrivers(drivers: List<DriverEntity>)

    @Update
    suspend fun update(driver: DriverEntity)

    @Delete
    suspend fun delete(driver: DriverEntity)

    @Query("SELECT * FROM route_table")
    fun getAllRoutes(): Flow<List<RouteEntity>>

    @Query("SELECT * FROM route_table WHERE id = :id AND name = :name AND type = :type")
    fun getById(id: Int, name: String, type: String): Flow<RouteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(route: RouteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutes(routes: List<RouteEntity>)

    @Update
    suspend fun update(route: RouteEntity)

    @Delete
    suspend fun delete(route: RouteEntity)
}
