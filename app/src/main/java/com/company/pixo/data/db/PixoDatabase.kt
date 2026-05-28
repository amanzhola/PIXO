package com.company.pixo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.company.pixo.data.db.dao.HistoryDao
import com.company.pixo.data.db.entity.HistoryEntity

@Database(
    entities = [
        HistoryEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class PixoDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}