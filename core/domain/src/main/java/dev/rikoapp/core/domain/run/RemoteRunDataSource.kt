package dev.rikoapp.core.domain.run

import dev.rikoapp.core.domain.util.DataError
import dev.rikoapp.core.domain.util.EmptyDataResult
import dev.rikoapp.core.domain.util.Result

interface RemoteRunDataSource {
    suspend fun getRuns(): Result<List<Run>, DataError.Network>
    suspend fun postRun(run: Run, mapPicture: ByteArray): Result<Run, DataError.Network>
    suspend fun deleteRun(id: String): EmptyDataResult<DataError.Network>
}