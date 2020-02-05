package com.example.challengeeasy.validator

import com.example.challengeeasy.repository.domain.validator.DateValidator
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class DateValidatorUnitTest {

    @Test
    fun givenDate_WhenValidate_ThenAssertTrue() {
        val valueTrue: Boolean = DateValidator.isValid("21/12/2021")
        assertTrue(valueTrue)
    }

    @Test
    fun givenDate_WhenValidate_ThenAssertFalse() {
        val valueFalse: Boolean = DateValidator.isValid("9999")
        assertFalse(valueFalse)
    }
}