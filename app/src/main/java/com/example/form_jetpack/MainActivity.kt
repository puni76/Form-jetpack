package com.example.form_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.form_jetpack.app.MedicationApp
import com.example.form_jetpack.ui.theme.FormjetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormjetpackTheme {
                    MedicationApp()
            }
        }
    }
}

