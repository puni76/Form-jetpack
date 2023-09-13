package com.example.form_jetpack.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import com.example.form_jetpack.R

@SuppressLint("UnrememberedMutableState")
  fun validateMedication(
    name:String,
    dosage:Int,
    recurrence: String,
    endDate:Long,
    morningSelection:Boolean,
    afternoonSelection: Boolean,
    eveningSelection:Boolean,
    nightSelection: Boolean,
    onInvalidate:(Int) -> Unit,
    onValidate: () -> Unit
){
    if (name.isEmpty()){
        onInvalidate(R.string.medication_name)
        return
    }
    if (dosage<1){
        onInvalidate(R.string.dosage)
        return
    }
    if (endDate < 1){
        onInvalidate(R.string.end_date)
        endDate
    }
    if (!morningSelection && !afternoonSelection && !eveningSelection && !nightSelection){
        onInvalidate(R.string.times_of_day)
        return
    }
    val timesOfDay = mutableStateListOf<TimesOfDay>()
    if (morningSelection) timesOfDay.add(TimesOfDay.Morning)
    if (afternoonSelection) timesOfDay.add(TimesOfDay.Afternoon)
    if (eveningSelection) timesOfDay.add(TimesOfDay.Evening)
    if (nightSelection) timesOfDay.add(TimesOfDay.Night)
    onValidate()
}