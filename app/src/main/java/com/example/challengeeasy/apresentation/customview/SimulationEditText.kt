package com.example.challengeeasy.apresentation.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.appcompat.widget.AppCompatEditText
import com.example.challengeeasy.R
import com.example.challengeeasy.apresentation.Mask
import com.example.challengeeasy.repository.domain.validator.*


class SimulationEditText(context: Context, attributeSet: AttributeSet) :
    AppCompatEditText(context, attributeSet) {

    private var validator: Validator? = null
    private var validationListener: ValidationListener? = null
    private val stateError = intArrayOf(R.attr.state_error)
    private var isEdited = false
    private var isError = false
    private var isFocus = false

    init {
        val attributes =
            context.obtainStyledAttributes(attributeSet,
                R.styleable.SimulationEditText
            )

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
        if (isError) {
            View.mergeDrawableStates(drawableState, stateError)
        }
        return drawableState
    }

    private fun setupMonetaryType() {
        addTextChangedListener(Mask.brazilianMonetaryFormat(this))
        initValidator(AmountValidator)
    }

    private fun setupPercentType() {
        addTextChangedListener(Mask.percentageFormat(this))
        initValidator(NoValidator)
    }

    private fun setupDateType() {
        addTextChangedListener(Mask.mask("##/##/####", this))
        initValidator(DateValidator)
    }

    private fun initValidator(validator: Validator) {
        this.validator = validator

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                isEdited = true
                forceUpdateState()
                validationListener?.validate()
            }
        })

        onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            this.isFocus = hasFocus
            updateState()
        }
    }

    private fun updateState() {
        validator?.let {
            isError = !isFocus && !it.isValid(text.toString()) && isEdited
            refreshDrawableState()
        }
    }

    fun forceUpdateState() {
        validator?.let {
            isError = !it.isValid(text.toString())
            refreshDrawableState()
        }
    }

    fun isValid(): Boolean = validator?.isValid(text.toString()) ?: false

    fun setValidationListener(validationListener: ValidationListener) {
        this.validationListener = validationListener
    }
}