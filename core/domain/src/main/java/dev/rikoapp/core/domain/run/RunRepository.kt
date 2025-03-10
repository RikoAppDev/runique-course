package dev.rikoapp.core.domain.run

import dev.rikoapp.core.domain.util.DataError
import dev.rikoapp.core.domain.util.EmptyDataResult
import kotlinx.coroutines.flow.Flow

interface RunRepository {
    fun getRuns(): Flow<List<Run>>
    suspend fun fetchRuns(): EmptyDataResult<DataError>
    suspend fun upsertRun(run: Run, mapPicture: ByteArray): EmptyDataResult<DataError>
    suspend fun deleteRun(id: RunId)
    suspend fun syncPendingRuns()
    suspend fun deleteAllRuns()
    suspend fun logout(): EmptyDataResult<DataError.Network>
}