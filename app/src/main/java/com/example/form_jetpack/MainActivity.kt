package com.example.form_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.form_jetpack.app.MedicationApp
import com.example.form_jetpack.viewModels.MedicationViewModel
import com.example.form_jetpack.screens.AddMedicationScreen
import com.example.form_jetpack.ui.theme.FormjetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: MedicationViewModel = viewModel()
            FormjetpackTheme {
                NavHost(
                    navController = navController,
                    startDestination = "medicationApp"
                ) {
                    composable("addMedication") {
                        // Your AddMedicationScreen composable
                        AddMedicationScreen(navController, viewModel)
                    }
                    composable("medicationApp") {
                        // Your MedicationApp composable
                        MedicationApp(navController, viewModel)
                    }
                }
            }
        }
    }
}

