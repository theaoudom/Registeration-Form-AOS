package com.example.registerform

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FormVm : ViewModel(){
    var userData = mutableStateOf(FormData())

    fun updateName(newData: String){
        userData.value = userData.value.copy(
            fullName = newData
        )
    }

    fun updateGender(newData: String){
        userData.value = userData.value.copy(
            gender = newData
        )
    }

    fun updatePhone(newData: String){
        userData.value = userData.value.copy(
            phone = newData
        )
    }

    fun updateAddress(newData: String){
        userData.value = userData.value.copy(
            address = newData
        )
    }
}