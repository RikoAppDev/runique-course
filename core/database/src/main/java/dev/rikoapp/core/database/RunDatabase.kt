package dev.rikoapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.rikoapp.core.database.dao.RunDao
import dev.rikoapp.core.database.entity.RunEntity

@Database(
    entities = [RunEntity::class],
    version = 1
)
abstract class RunDatabase : RoomDatabase() {
    abstract fun runDao(): RunDao
}