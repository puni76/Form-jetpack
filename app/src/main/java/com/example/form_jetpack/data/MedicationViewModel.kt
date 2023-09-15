package com.example.form_jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MedicationViewModel : ViewModel() {
    // LiveData to observe medication details
    val medicationDetails = MutableLiveData<MedicationDetails>()

    // List to hold all saved medications
    val medicationList = mutableListOf<MedicationDetails>()

    // Function to add a new medication
    fun addMedication(medication: MedicationDetails) {
        medicationList.add(medication)
        medicationDetails.value = medication
    }
}
