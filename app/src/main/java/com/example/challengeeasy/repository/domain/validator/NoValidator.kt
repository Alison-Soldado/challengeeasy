package com.example.challengeeasy.repository.domain.validator

object NoValidator : Validator {
    override fun isValid(value: String) = true
}