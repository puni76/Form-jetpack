package com.example.form_jetpack.util

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.form_jetpack.R
import com.example.form_jetpack.extention.toFormattedString
import java.text.DateFormatSymbols
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EndDateTextField(endDate: (Long) -> Unit){

    
    Text(
        text = stringResource(id = R.string.end_date),
        style = MaterialTheme.typography.bodyLarge,
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed : Boolean by interactionSource.collectIsPressedAsState()

    val currentDate = Date().toFormattedString()
    var selectedDate by rememberSaveable { mutableStateOf(currentDate) }

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year:Int = calendar.get(Calendar.YEAR)
    val month:Int = calendar.get(Calendar.MONTH)
    val day :Int = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

     val datePickerDialog =
         DatePickerDialog(context,{_:DatePicker,year:Int,month:Int,dayOfMonth:Int ->
             run {
                 val newDate = Calendar.getInstance()
                 newDate.set(year, month, dayOfMonth)
                 selectedDate = "${month.toMonthName()} $dayOfMonth ,$year"
                 endDate(newDate.timeInMillis)
             }
         },year,month,day)

    TextField(
        modifier=Modifier.fillMaxWidth(),
        readOnly=true,
        value = selectedDate,
        onValueChange = {},
        trailingIcon = {Icons.Default.DateRange},
        interactionSource = interactionSource
    )
    if (isPressed){
        datePickerDialog.show()
    }
}
fun Int.toMonthName():String{
    return DateFormatSymbols().months[this]
}