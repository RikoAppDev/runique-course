package dev.rikoapp.core.android_test

import dev.rikoapp.core.domain.AuthInfo
import dev.rikoapp.core.domain.SessionStorage

class SessionStorageFake : SessionStorage {

    private var authInfo: AuthInfo? = null

    override suspend fun get(): AuthInfo? {
        return authInfo
    }

    override suspend fun set(info: AuthInfo?) {
        authInfo = info
    }
}