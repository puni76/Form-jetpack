package com.example.form_jetpack.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.form_jetpack.data.MedicationDetails

class MedicationViewModel : ViewModel() {
    // LiveData to observe medication details
    val medicationDetails = MutableLiveData<MedicationDetails>()

    // List to hold all saved medications
    val medicationList = mutableListOf<MedicationDetails>()

    // Function to add a new medication
    fun addMedication(medication: MedicationDetails) {
        medicationList.add(medication)
        medicationDetails.value = medication
        medications.add(medication)

    }
    fun deleteMedication(medication: MedicationDetails) {
        medicationList.remove(medication)
        medicationDetails.value = medication
        medications.remove(medication)
    }
    private val medications: MutableList<MedicationDetails> = mutableListOf()

    // Retrieve all saved medications
    fun getAllMedications(): List<MedicationDetails> {
        return medications.toList()
    }
}
