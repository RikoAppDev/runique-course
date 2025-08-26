package dev.rikoapp.auth.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserDataValidatorTest {
    private lateinit var userDataValidator: UserDataValidator

    @BeforeEach
    fun setUp() {
        userDataValidator = UserDataValidator(
            patternValidator = object : PatternValidator {
                override fun matches(value: String): Boolean {
                    return true
                }
            }
        )
    }

    @Test
    fun `validatePassword returns true for a valid password`() {
        val password = "Test12345"

        val state = userDataValidator.validatePassword(password)

        assertThat(state.isValidPassword).isEqualTo(true)
    }
}