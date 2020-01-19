package com.example.challengeeasy

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText


class SimulationEditText(context: Context, attributeSet: AttributeSet) :
    AppCompatEditText(context, attributeSet) {

    private var uiValidator: UiValidator? = null
    private var validationListener: ValidationListener? = null
    private val stateError = intArrayOf(R.attr.state_error)
    private var hasBeenEdited = false
    private var shouldShowError = false
    private var hasFocus = false

    init {
        val attributes =
            context.obtainStyledAttributes(attributeSet, R.styleable.SimulationEditText)

        when (attributes.getString(0)) {
            TypeEnum.MONETARY.type -> setupMonetaryType()
            TypeEnum.DATE.type -> setupDateType()
            TypeEnum.PERCENTAGE.type -> setupPercentType()
            else -> Unit
        }

        attributes.recycle()
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 2)
        if (shouldShowError) {
            View.mergeDrawableStates(drawableState, stateError)
        }
        return drawableState
    }

    private fun setupMonetaryType() {
        addTextChangedListener(Mask.brazilianMonetaryFormat( this))
        initValidator(AmountValidator)
    }

    private fun setupPercentType() {
        addTextChangedListener(Mask.percentageFormat(this))
        initValidator(NoValidator)
    }

    private fun setupDateType() {
        addTextChangedListener(Mask.mask( "##/##/####",this))
        initValidator(DateValidator)
    }

    private fun initValidator(validator: UiValidator) {
        uiValidator = validator

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                hasBeenEdited = true
                forceUpdateState()
                validationListener?.validate()
            }
        })

        onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            this.hasFocus = hasFocus
            updateState()
        }
    }

    private fun updateState() {
        shouldShowError = !hasFocus && !uiValidator?.isValid(text.toString())!! && hasBeenEdited
        refreshDrawableState()
    }

    fun forceUpdateState() {
        shouldShowError = !uiValidator?.isValid(text.toString())!!
        refreshDrawableState()
    }

    fun isValid(): Boolean {
        return uiValidator?.isValid(text.toString()) ?: false
    }

    fun setValidationListener(validationListener: ValidationListener) {
        this.validationListener = validationListener
    }
}