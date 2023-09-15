    package com.example.form_jetpack.app

    import android.annotation.SuppressLint
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Spacer

    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
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
    import androidx.navigation.compose.rememberNavController
    import com.example.form_jetpack.R
    import com.example.form_jetpack.data.MedicationViewModel


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
    @Composable
    fun MedicationApp(navController: NavHostController, viewModel: MedicationViewModel) {
        val medicationDetails = viewModel.medicationDetails.observeAsState()

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
                    medicationDetails.value?.let { details ->
                        Column {
                            Spacer(modifier = Modifier.padding(20.dp))
                            Text("1 Medication Name: ${details.medicationName}")
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text("2 Number of Dosage: ${details.numberOfDosage}")
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text("3 Recurrence: ${details.recurrence}")
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text("4 EndDate: ${details.endDate}")
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text("5 Morning Selection: ${details.morningSelection}")
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text("6 Afternoon Selection: ${details.afternoonSelection}")
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text("7 Evening Selection: ${details.eveningSelection}")
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text("8 Night Selection: ${details.nightSelection}")
                        }
                    }
                }
            }
        )
    }