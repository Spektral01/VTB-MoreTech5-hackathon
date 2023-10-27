package com.itsc.tuwoda

import android.content.Intent
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel:ViewModel() {

    val stateButton = listOf<MutableState<Boolean>>(
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
    )
    var stateAtmOrOffice by mutableStateOf(false)
    var stateSerch by mutableStateOf(false)

    var curOffice: Office = Office(
        address = "",
        distance = 0
    )








    var stateMap by mutableStateOf(true)
    var stateRoutes by mutableStateOf(false)
    var stateMe by mutableStateOf(false)
    var stateSailing by mutableStateOf(false)
    var stateMapDialog by mutableStateOf(false)
    var stateTextTitleSailing by mutableStateOf("")

    fun updateStateMap() {
        stateMap = true
        stateMe = false
        stateRoutes = false
        stateSailing = false
    }
    fun updateStateRoutes() {
        stateRoutes = true
        stateMe = false
        stateMap = false
        stateSailing = false
    }
    fun updateStateMe() {
        stateMe = true
        stateMap = false
        stateRoutes = false
        stateSailing = false
    }
    fun updateStateSailing() {
        stateSailing = true
        stateMe = false
        stateMap = false
        stateRoutes = false

    }
}
