package com.ashish.core.util

import java.text.SimpleDateFormat
import java.util.*

/***
 * Converts date to required string format
 */
fun String.toDateString(): String {
    return if (this != "null") {
        SimpleDateFormat(
            "hh:mm a dd/MMMM/yyyy",
            Locale.ENGLISH
        ).format(this.toLong().times(1000).let { Date(it) })
            .replace("AM", "am")
            .replace("PM", "pm")
            .replace("/", " ")
    } else {
        ""
    }

}