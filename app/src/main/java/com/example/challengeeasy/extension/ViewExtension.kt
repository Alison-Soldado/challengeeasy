package com.example.challengeeasy.extension

import android.view.View
import android.widget.Toast


fun View.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, message ?: "Something went wrong", duration).show()
}