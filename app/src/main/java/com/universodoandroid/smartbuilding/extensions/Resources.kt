package com.universodoandroid.smartbuilding.extensions

import android.content.res.Configuration
import android.content.res.Resources

fun Resources.numberOfColumns(): Int {
    return if (this.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        1
    } else {
        2
    }
}