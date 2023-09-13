package com.example.form_jetpack.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.form_jetpack.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecurrenceDropDownMenu(recurrence: (String) -> Unit){
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = stringResource(id = R.string.recurrence),
            style = MaterialTheme.typography.bodyLarge
        )

        val options = getRecurrenceList().map { it.name }
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }
        
        ExposedDropdownMenuBox(
            expanded = expanded ,
            onExpandedChange = { expanded = !expanded}
        ) {
           TextField(
               modifier = Modifier.menuAnchor(),
               readOnly = true,
               value =selectedOptionText ,
               trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
               onValueChange = {},
               colors = ExposedDropdownMenuDefaults.textFieldColors()
           )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach{ selectOption ->
                    DropdownMenuItem(
                        text = { selectOption },
                        onClick = {
                            selectedOptionText = selectOption
                            recurrence(selectOption)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}