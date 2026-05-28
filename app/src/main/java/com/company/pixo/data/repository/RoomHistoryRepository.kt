package com.company.pixo.data.repository

import com.company.pixo.data.db.dao.HistoryDao
import com.company.pixo.data.db.entity.HistoryEntity
import com.company.pixo.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

class RoomHistoryRepository(
    private val historyDao: HistoryDao
) : HistoryRepository {

    override fun observeHistory(): Flow<List<HistoryEntity>> {
        return historyDao.observeHistory()
    }

    override suspend fun deleteById(id: String) {
        historyDao.deleteById(id)
    }

    override suspend fun clear() {
        historyDao.clear()
    }
}

//package com.company.pixo.data.repository
//
//import com.company.pixo.data.db.dao.HistoryDao
//import com.company.pixo.data.db.entity.HistoryEntity
//import com.company.pixo.domain.repository.HistoryRepository
//import kotlinx.coroutines.flow.Flow
//
//class RoomHistoryRepository(
//    private val historyDao: HistoryDao
//) : HistoryRepository {
//
//    override fun observeHistory(): Flow<List<HistoryEntity>> {
//        return historyDao.observeHistory()
//    }
//
//    override suspend fun deleteById(id: String) {
//        val items = historyDao.observeHistory()
//        // не используем так, потому что Flow.
//        // Добавим deleteById в DAO ниже.
//    }
//
//    override suspend fun clear() {
//        historyDao.clear()
//    }
//}