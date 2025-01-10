package dev.rikoapp.auth.data

import dev.rikoapp.auth.domain.AuthRepository
import dev.rikoapp.core.data.networking.post
import dev.rikoapp.core.domain.AuthInfo
import dev.rikoapp.core.domain.SessionStorage
import dev.rikoapp.core.domain.util.DataError
import dev.rikoapp.core.domain.util.EmptyDataResult
import dev.rikoapp.core.domain.util.Result
import dev.rikoapp.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(
                email = email,
                password = password
            )
        )

        if (result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }

        return result.asEmptyDataResult()
    }

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