package com.polendina.egyptpostalcode.presentation.screens.contact

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ContactViewModel: ViewModel() {
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var message by mutableStateOf("")
    val termsOfService by mutableStateOf(false)
    val submitButtonEnabled by mutableStateOf(listOf(name, email, message).all { it.isNotEmpty() })
}