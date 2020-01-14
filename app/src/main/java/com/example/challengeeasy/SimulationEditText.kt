package com.example.challengeeasy

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import com.example.challengeeasy.extension.toast

class SimulationEditText(context: Context, attributeSet: AttributeSet) :
    AppCompatEditText(context, attributeSet) {

    private var validationListener: ValidationListener? = null

    init {
        val attributes =
            context.obtainStyledAttributes(attributeSet, R.styleable.SimulationEditText)

        when (attributes.getString(0)) {
            TypeEnum.MONETARY.name -> {
                toast("Monetary")
            }
            TypeEnum.DATE.name -> {
                addTextChangedListener { toast("Date") }
            }
            TypeEnum.PERCENTAGE.name -> {
                addTextChangedListener { toast("Percentage") }
            }
            else -> Unit
        }

        attributes.recycle()
    }

    fun setValidationListener(validationListener: ValidationListener) {
        this.validationListener = validationListener
    }
}