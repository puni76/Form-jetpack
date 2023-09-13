package com.example.form_jetpack.extention

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toFormattedString():String{
    val simpleDateFormat = SimpleDateFormat("LLLL, dd,yyyy", Locale.getDefault())
    return simpleDateFormat.format(this)
}