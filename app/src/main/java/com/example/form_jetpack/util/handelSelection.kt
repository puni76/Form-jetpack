package com.example.form_jetpack.util

 fun handelSelection(
    isSelected:Boolean,
    selectionCount:Int,
    canSelectMoreTimesOfDay:Boolean,
    onStateChange:(Int,Boolean) ->Unit,
    onShowMaxSelectionError : () -> Unit,
){
    if (isSelected){
        onStateChange(selectionCount -1 ,!isSelected)
    } else {
        if (canSelectMoreTimesOfDay){
            onStateChange(selectionCount + 1 ,!isSelected)
        } else {
            onShowMaxSelectionError()
        }
    }
}