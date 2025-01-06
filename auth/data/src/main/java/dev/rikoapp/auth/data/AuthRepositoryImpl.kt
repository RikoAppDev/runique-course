package dev.rikoapp.auth.data

import dev.rikoapp.auth.domain.AuthRepository
import dev.rikoapp.core.data.networking.post
import dev.rikoapp.core.domain.util.DataError
import dev.rikoapp.core.domain.util.EmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
) : AuthRepository {
    override suspend fun register(
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(email = email, password = password)
        )
    }
}