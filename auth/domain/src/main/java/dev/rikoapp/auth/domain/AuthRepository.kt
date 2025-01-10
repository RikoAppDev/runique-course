package dev.rikoapp.auth.domain

import dev.rikoapp.core.domain.util.DataError
import dev.rikoapp.core.domain.util.EmptyDataResult

interface AuthRepository {
    suspend fun login(email: String, password: String): EmptyDataResult<DataError.Network>
    suspend fun register(email: String, password: String): EmptyDataResult<DataError.Network>
}