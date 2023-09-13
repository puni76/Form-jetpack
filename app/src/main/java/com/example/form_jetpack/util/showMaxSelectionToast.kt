package com.example.form_jetpack.util

import android.content.Context
import android.widget.Toast

 fun showMaxSelectionToast(
    numberOfDosage: String,
    context: Context
){
    Toast.makeText(
        context,
        "You're selecting ${(numberOfDosage.toIntOrNull() ?: 0) + 1}" +
                " time(s) of days which is more than the number of dosage",
        Toast.LENGTH_LONG
    ).show()
}