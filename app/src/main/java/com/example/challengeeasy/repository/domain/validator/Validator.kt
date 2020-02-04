package com.example.challengeeasy.repository.domain.validator

interface Validator {
    fun isValid(value: String): Boolean
}