package com.example.challengeeasy.extension

import android.app.Activity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun Activity.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message ?: "Something went wrong", duration).show()
}

fun ProgressBar.visibilityLoading(visibility: Boolean) {
    if (visibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
