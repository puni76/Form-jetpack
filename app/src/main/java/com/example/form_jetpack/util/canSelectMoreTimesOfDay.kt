package com.example.form_jetpack.util

 fun canSelectMoreTimesOfDay(selectionCount: Int,numberOfDosage:Int):Boolean {
    return selectionCount < numberOfDosage
}