package com.example.challengeeasy.customview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.challengeeasy.R
import com.example.challengeeasy.bindView

class ResultTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val textViewText: AppCompatTextView by bindView(R.id.text_view_result_text)
    private val textViewValue: AppCompatTextView by bindView(R.id.text_view_result_value)

    init {
        inflate(context, R.layout.text_view_result, rootView as ViewGroup?)
        context.theme.obtainStyledAttributes(attributeSet,
            R.styleable.ResultTextView, 0, 0)
            .apply {
                try {
                    textViewText.text = getText(R.styleable.ResultTextView_text_result)
                } finally {
                    recycle()
                }
            }
    }

    fun setText(resultValue: String) {
        textViewValue.text = resultValue
    }
}