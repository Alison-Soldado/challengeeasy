package com.example.challengeeasy.validator

import com.example.challengeeasy.repository.domain.validator.AmountValidator
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class AmountValidatorUnitTest {

    @Test
    fun givenValue_WhenValidate_ThenAssertTrue() {
        val valueTrue: Boolean = AmountValidator.isValid("100")
        assertTrue(valueTrue)
    }

    @Test
    fun givenValue_WhenValidate_ThenAssertFalse() {
        val valueFalse: Boolean = AmountValidator.isValid("unit test")
        assertFalse(valueFalse)
    }
}