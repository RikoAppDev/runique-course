package dev.rikoapp.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}
