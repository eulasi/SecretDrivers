package com.example.secretdrivers.data.db.sddriver

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RouteDao {

    @Query("SELECT * FROM route_table")
    fun getAllRoutes(): Flow<List<RouteEntity>>

    @Query("SELECT * FROM route_table WHERE name = :id AND name = :name AND type = :type")
    fun getById(id: Int, name: String, type: String): Flow<RouteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg route: RouteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutes(routes: List<RouteEntity>)

    @Update
    suspend fun update(vararg route: RouteEntity)

    @Delete
    suspend fun delete(vararg route: RouteEntity)
}