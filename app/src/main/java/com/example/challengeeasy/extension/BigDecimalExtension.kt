package com.example.challengeeasy.extension

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

private const val START_RANGE = 3
private const val END_RANGE = 2

fun BigDecimal.toBrazilCurrency() = NumberFormat
    .getCurrencyInstance(Locale("pt_BR", "BR"))
    .format(this).run {
        replaceRange(
            length - START_RANGE,
            length - END_RANGE,
            ",")
    }