package com.example.challengeeasy.extension


fun Double.toPercent() = String.format("%.2f", this).plus("%")