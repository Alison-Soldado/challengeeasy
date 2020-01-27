package com.example.challengeeasy.extension

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.challengeeasy.R

fun AppCompatTextView.toSpannableColorPrimary(
    text: String,
    indexStart: Int = 0,
    indexEnd: Int = 0
) {
    val spannable = SpannableString(text)
    spannable.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)),
        indexStart, indexEnd,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    setText(spannable)
}