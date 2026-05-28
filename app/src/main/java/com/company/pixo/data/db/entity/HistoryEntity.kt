package com.company.pixo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey
    val id: String,
    val toolType: String,
    val sourceImageUri: String?,
    val resultImageUrl: String?,
    val status: String,
    val prompt: String?,
    val createdAt: Long,
    val errorMessage: String?
)