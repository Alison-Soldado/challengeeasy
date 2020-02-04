package com.example.challengeeasy.apresentation.extension

import java.text.SimpleDateFormat
import java.util.*


private const val ONE_HUNDRED = 100

fun String.toServerCurrency() =
    this.replace("R$", "")
        .replace(".", "")
        .replace(",", "")
        .trim()
        .toDouble() / ONE_HUNDRED

fun String.toDisplayDate() = this.toDate("yyyy-MM-dd'T'HH:mm:ss", "dd/MM/yyyy")

fun String.toServerDate() = this.toDate("dd/MM/yyyy", "yyyy-MM-dd")

fun String.toServerPercentage() = this.replace("%", "")

fun String.toDate(input: String, output: String): String {
    val locale = Locale("pt-BR")
    val inputFormat = SimpleDateFormat(input, locale)
    val outputFormat = SimpleDateFormat(output, locale)
    val d = inputFormat.parse(this)
    return outputFormat.format(d)
}