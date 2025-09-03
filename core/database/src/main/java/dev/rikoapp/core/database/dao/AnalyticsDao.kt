package dev.rikoapp.core.database.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AnalyticsDao {

    @Query("SELECT SUM(distanceMeters) FROM runentity")
    suspend fun getTotalDistance(): Int

    @Query("SELECT SUM(durationMillis) FROM runentity")
    suspend fun getTotalTimeRun(): Long

    @Query("SELECT MAX(maxSpeedKmh) FROM runentity")
    suspend fun getMaxRunSpeed(): Double

    @Query("SELECT AVG(distanceMeters) FROM runentity")
    suspend fun getAvgDistancePerRun(): Double

    @Query("SELECT AVG((durationMillis / 60000.0) / (distanceMeters / 1000.0)) FROM runentity")
    suspend fun getAvgPacePerRun(): Double

    @Query("SELECT AVG(avgHeartRate) FROM runentity WHERE avgHeartRate IS NOT NULL")
    suspend fun getAvgHeartRate(): Int?

    @Query("SELECT MAX(maxHeartRate) FROM runentity WHERE maxHeartRate IS NOT NULL")
    suspend fun getMaxHeartRate(): Int?
}