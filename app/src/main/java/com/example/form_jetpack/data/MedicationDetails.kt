package com.example.form_jetpack.data


data class MedicationDetails(
    val medicationName: String,
    val numberOfDosage: Int,
    val recurrence:String,
    val endDate: Long,
    val morningSelection: Boolean,
    val afternoonSelection: Boolean,
    val eveningSelection: Boolean,
    val nightSelection: Boolean,

    )