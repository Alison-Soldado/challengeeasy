package com.example.challengeeasy.repository.domain.validator


object AmountValidator : Validator {

    private const val ONE_HUNDRED = 100
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