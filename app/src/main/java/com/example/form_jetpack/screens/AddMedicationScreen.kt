package com.example.form_jetpack.screens

import android.annotation.SuppressLint

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.form_jetpack.R
import com.example.form_jetpack.data.MedicationDetails
import com.example.form_jetpack.viewModels.MedicationViewModel
import com.example.form_jetpack.util.EndDateTextField
import com.example.form_jetpack.util.Recurrence
import com.example.form_jetpack.util.RecurrenceDropDownMenu
import com.example.form_jetpack.util.TimesOfDay
import com.example.form_jetpack.util.canSelectMoreTimesOfDay
import com.example.form_jetpack.util.handelSelection
import com.example.form_jetpack.util.showMaxSelectionToast
import com.example.form_jetpack.util.validateMedication
import java.util.Date

@SuppressLint("StringFormatInvalid")
@OptIn(ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun AddMedicationScreen(navController: NavHostController, viewModel: MedicationViewModel) {
    var medicationName by rememberSaveable { mutableStateOf("") }
    var numberOfDosage by rememberSaveable { mutableStateOf("1") }
    var recurrence by rememberSaveable { mutableStateOf(Recurrence.Daily.name) }
    var endDate by rememberSaveable { mutableStateOf(Date().time) }
    var isMorningSelected by rememberSaveable { mutableStateOf(false) }
    var isAfternoonSelected by rememberSaveable { mutableStateOf(false) }
    var isEveningSelected by rememberSaveable { mutableStateOf(false) }
    var isNightSelected by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardHeight = remember { mutableStateOf(0) }

    Column (
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = stringResource(id = R.string.add_medication),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = stringResource(id = R.string.medication_name),
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth().imePadding(),
            value = medicationName,
            onValueChange = {medicationName =it},
            placeholder = { Text(text = "e.g. examine")},
                    keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }),
        )

        Spacer(modifier = Modifier.padding(4.dp))

        var isMaxDoseError by rememberSaveable { mutableStateOf(false) }

        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            val maxDose = 2

            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text = stringResource(id = R.string.dosage),
                    style = MaterialTheme.typography.bodyLarge
                )

               TextField(
                   modifier=Modifier.width(125.dp),
                   value = numberOfDosage,
                   onValueChange = {
                   if (it.length < maxDose){
                       isMaxDoseError = false
                       numberOfDosage = it
                   } else {
                       isMaxDoseError = true
                   }
               },
                   trailingIcon = {
                       if (isMaxDoseError){
                           Icon(
                               imageVector = Icons.Filled.Info,
                               contentDescription = "Error",
                               tint = MaterialTheme.colorScheme.error
                           )

                       }
                   },
                   placeholder = { Text(text = " e.g 1")},
                   isError = isMaxDoseError,
                   keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
               )
            }
            RecurrenceDropDownMenu { recurrence =it}
        }
        if (isMaxDoseError){
            Text(
                text = "You cannot have more dosage per day ",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))

        EndDateTextField{endDate=it}

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = stringResource(id = R.string.times_of_day),
            style =  MaterialTheme.typography.bodyLarge
        )
        var selectionCount by rememberSaveable { mutableStateOf(0) }
        val context = LocalContext.current

        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            FilterChip(
                modifier=Modifier.fillMaxWidth(.5f),
                selected = isMorningSelected,
                onClick = {
                          handelSelection(
                              isSelected = isMorningSelected,
                              selectionCount= selectionCount,
                              canSelectMoreTimesOfDay = canSelectMoreTimesOfDay(selectionCount,
                                 numberOfDosage.toIntOrNull() ?: 0),
                              onStateChange ={count , selected ->
                                  isMorningSelected = selected
                                  selectionCount = count
                              },
                              onShowMaxSelectionError = {
                                  showMaxSelectionToast(numberOfDosage,context)
                              }
                          )
                },
                label = { Text(text = TimesOfDay.Morning.name)},
                leadingIcon = { Icon(imageVector = Icons.Default.Done, contentDescription ="Selected" ) }
            )
            FilterChip(
                modifier=Modifier.fillMaxWidth(),
                selected = isAfternoonSelected,
                onClick = {
                    handelSelection(
                        isSelected = isAfternoonSelected,
                        selectionCount= selectionCount,
                        canSelectMoreTimesOfDay = canSelectMoreTimesOfDay(selectionCount,
                            numberOfDosage.toIntOrNull() ?: 0),
                        onStateChange ={count , selected ->
                            isAfternoonSelected = selected
                            selectionCount = count
                        },
                        onShowMaxSelectionError = {
                            showMaxSelectionToast(numberOfDosage,context)
                        }
                    )
                },
                label = { Text(text = TimesOfDay.Afternoon.name)},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Done, contentDescription ="Selected" )
                }
            )
        }

        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            FilterChip(
                modifier=Modifier.fillMaxWidth(.5f),
                selected = isEveningSelected,
                onClick = {
                    handelSelection(
                        isSelected = isEveningSelected,
                        selectionCount= selectionCount,
                        canSelectMoreTimesOfDay = canSelectMoreTimesOfDay(selectionCount,
                            numberOfDosage.toIntOrNull() ?: 0),
                        onStateChange ={count , selected ->
                            isEveningSelected = selected
                            selectionCount = count
                        },
                        onShowMaxSelectionError = {
                            showMaxSelectionToast(numberOfDosage,context)
                        }
                    )
                },
                label = { Text(text = TimesOfDay.Evening.name)},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Done, contentDescription ="Selected" )
                }
            )
            FilterChip(
                modifier=Modifier.fillMaxWidth(),
                selected = isNightSelected,
                onClick = {
                    handelSelection(
                        isSelected = isNightSelected,
                        selectionCount= selectionCount,
                        canSelectMoreTimesOfDay = canSelectMoreTimesOfDay(selectionCount,
                            numberOfDosage.toIntOrNull() ?: 0),
                        onStateChange ={count , selected ->
                            isNightSelected = selected
                            selectionCount = count
                        },
                        onShowMaxSelectionError = {
                            showMaxSelectionToast(numberOfDosage,context)
                        }
                    )
                },
                label = { Text(text = TimesOfDay.Night.name)},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Done, contentDescription ="Selected" )
                }
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(

            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                val medication = MedicationDetails(
                    medicationName = medicationName,
                    numberOfDosage = numberOfDosage.toIntOrNull() ?: 0,
                    recurrence = recurrence,
                    endDate = endDate,
                    morningSelection = isMorningSelected,
                    afternoonSelection = isAfternoonSelected,
                    eveningSelection = isEveningSelected,
                    nightSelection = isNightSelected
                )

                viewModel.addMedication(medication)
                // Navigate to MedicationApp after saving
                navController.navigate("medicationApp")

                validateMedication(
                    name = medicationName,
                    dosage = numberOfDosage.toIntOrNull() ?: 0,
                    recurrence = recurrence,
                    endDate = endDate,
                    morningSelection = isMorningSelected,
                    afternoonSelection = isAfternoonSelected,
                    eveningSelection = isEveningSelected,
                    nightSelection = isNightSelected,
                    onInvalidate = {
                        Toast.makeText(
                            context,
                            context.getString(R.string.value_is_empty,context.getString(it)),
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    onValidate = {
                        Toast.makeText(
                            context,
                            context.getString(R.string.success,),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            },
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Text(
                text = stringResource(id = R.string.save),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}







