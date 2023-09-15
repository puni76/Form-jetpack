package com.example.form_jetpack.extention

import androidx.core.net.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toFormattedString():String{
    val simpleDateFormat = SimpleDateFormat("LLLL, dd,yyyy", Locale.getDefault())
    return simpleDateFormat.format(this)
}

fun formatEndDate(month: String, day: String, year: String): String {
    val inputDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
    val outputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    val formattedDate = "$month $day, $year"

    return try {
        val parsedDate = inputDateFormat.parse(formattedDate)
        outputDateFormat.format(parsedDate)
    } catch (e: ParseException) {
        // Handle parsing error
        "Invalid Date"
    }
}
