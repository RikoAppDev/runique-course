package dev.rikoapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.rikoapp.core.database.dao.AnalyticsDao
import dev.rikoapp.core.database.dao.RunDao
import dev.rikoapp.core.database.dao.RunPendingSyncDao
import dev.rikoapp.core.database.entity.DeletedRunSyncEntity
import dev.rikoapp.core.database.entity.RunEntity
import dev.rikoapp.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [
        RunEntity::class,
        RunPendingSyncEntity::class,
        DeletedRunSyncEntity::class
    ],
    version = 1
)
abstract class RunDatabase : RoomDatabase() {
    abstract val runDao: RunDao
    abstract val runPendingSyncDao: RunPendingSyncDao
    abstract val analyticsDao: AnalyticsDao
}