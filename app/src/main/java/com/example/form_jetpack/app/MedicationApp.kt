    package com.example.form_jetpack.app

    import android.annotation.SuppressLint
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Spacer

    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Add
    import androidx.compose.material3.FloatingActionButton
    import androidx.compose.material3.Icon
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.livedata.observeAsState
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.unit.dp
    import androidx.navigation.NavHostController
    import com.example.form_jetpack.R
    import com.example.form_jetpack.data.MedicationDetails
    import com.example.form_jetpack.viewModels.MedicationViewModel


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
    @Composable
    fun MedicationApp(navController: NavHostController, viewModel: MedicationViewModel) {
        val medicationDetails = viewModel.medicationDetails.observeAsState()
        val medicationList = mutableListOf<MedicationDetails>()
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("addMedication")
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Medication"
                    )
                }
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.medicne),
                        style =  MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.align(alignment = Alignment.TopCenter),
                        color = Color.Magenta
                    )

                    // Display all saved medications from the ViewModel
                    val medications = viewModel.getAllMedications()
                        LazyColumn {
                            for (medication in medications) {
                          item {   Spacer(modifier = Modifier.padding(20.dp))
                              Text("1 Medication Name: ${medication.medicationName}")
                          }
                            item { Spacer(modifier = Modifier.padding(4.dp))
                                Text("2 Number of Dosage: ${medication.numberOfDosage}") }
                            item { Spacer(modifier = Modifier.padding(4.dp))
                                Text("3 Recurrence: ${medication.recurrence}") }
                            item { Spacer(modifier = Modifier.padding(4.dp))
                                Text("4 EndDate: ${medication.endDate}") }
                            item { Spacer(modifier = Modifier.padding(4.dp))
                                Text("5 Morning Selection: ${medication.morningSelection}") }
                            item { Spacer(modifier = Modifier.padding(4.dp))
                                Text("6 Afternoon Selection: ${medication.afternoonSelection}") }
                            item { Spacer(modifier = Modifier.padding(4.dp))
                                Text("7 Evening Selection: ${medication.eveningSelection}") }
                            item { Spacer(modifier = Modifier.padding(4.dp))
                                Text("8 Night Selection: ${medication.nightSelection}") }
                        }
                    }
                }
            }
        )
    }