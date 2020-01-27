package com.example.challengeeasy.validator

import com.example.challengeeasy.ONE_HUNDRED

object AmountValidator : UiValidator {

    override fun isValid(value: String): Boolean {
        val validationValue = value.replace("R$", "")
            .replace(".", "")
            .replace(",", "")
            .trim()

        return when (validationValue.toFloatOrNull()) {
            null -> false
            else -> validationValue.toFloat() / ONE_HUNDRED > 0.00
        }
    }

}