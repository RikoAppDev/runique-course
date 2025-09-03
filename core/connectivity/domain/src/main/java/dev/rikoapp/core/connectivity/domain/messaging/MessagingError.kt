package dev.rikoapp.core.connectivity.domain.messaging

import dev.rikoapp.core.domain.util.Error

enum class MessagingError: Error {
    CONNECTION_INTERRUPTED,
    DISCONNECTED,
    UNKNOWN
}