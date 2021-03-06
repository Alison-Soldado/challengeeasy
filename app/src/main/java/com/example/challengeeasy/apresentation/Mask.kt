package com.example.challengeeasy.apresentation

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat
import java.util.*

class Mask{
    companion object {
        private fun replaceChars(fullDate : String) : String{
            return fullDate.replace(".", "").replace("-", "")
                .replace("(", "").replace(")", "")
                .replace("/", "").replace(" ", "")
                .replace("*", "")
        }

        fun mask(mask : String, editText : EditText) : TextWatcher {

            return object : TextWatcher {
                var isUpdating : Boolean = false
                var oldString : String = ""
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val str =
                        replaceChars(
                            s.toString()
                        )
                    var dateWithMask = ""

                    if (count == 0)//is deleting
                        isUpdating = true

                    if (isUpdating){
                        oldString = str
                        isUpdating = false
                        return
                    }

                    var i = 0
                    for (m : Char in mask.toCharArray()){
                        if (m != '#' && str.length > oldString.length){
                            dateWithMask += m
                            continue
                        }
                        try {
                            dateWithMask += str[i]
                        }catch (e : Exception){
                            break
                        }
                        i++
                    }

                    isUpdating = true
                    editText.setText(dateWithMask)
                    editText.setSelection(dateWithMask.length)

                }

                override fun afterTextChanged(editable: Editable) {}
            }
        }

        fun brazilianMonetaryFormat(ediTxt: EditText): TextWatcher {
            return object : TextWatcher {

                private var current = ""

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun afterTextChanged(s: Editable) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (s.toString() != current) {
                        val cleanString = s.toString().replace("[R$,.]".toRegex(), "").trim()

                        val parsed = java.lang.Double.parseDouble(cleanString)

                        current = NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(parsed / 100)
                        ediTxt.setText(current)
                        ediTxt.setSelection(current.length)
                    }
                }

            }
        }

        fun percentageFormat(ediTxt: EditText): TextWatcher {
            return object : TextWatcher {

                private var current = ""

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun afterTextChanged(s: Editable) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val changing = s.toString()

                    if (changing != current) {
                        val cleanString = if (current.isEmpty() || changing.contains("%")) {
                            changing.replace("[%]".toRegex(), "").trim()
                        } else {
                            changing.trim().dropLast(1)
                        }

                        current = if (cleanString.isNotEmpty()) {
                            val parsed = cleanString.toInt()
                            "$parsed%"
                        } else {
                            cleanString
                        }

                        ediTxt.setText(current)
                        ediTxt.setSelection(current.length)
                    }
                }

            }
        }

    }
}