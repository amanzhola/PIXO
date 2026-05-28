package com.company.pixo.domain.repository

import com.company.pixo.data.db.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun observeHistory(): Flow<List<HistoryEntity>>

    suspend fun deleteById(id: String)

    suspend fun clear()
}