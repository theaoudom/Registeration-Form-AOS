package com.example.registerform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class RegisterForm{
    @Preview(device = Devices.PIXEL, showBackground = true)
    @Composable
    fun RegisterFrom(){
        val viewModel = viewModel<FormVm>()
        val button = viewModel.userData.value.fullName.isNotEmpty() && viewModel.userData.value.gender.isNotEmpty() && viewModel.userData.value.phone.isNotEmpty() && viewModel.userData.value.address.isNotEmpty()
        Column (
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Registration Form", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "User", modifier = Modifier.size(40.dp))
            }
            TextComponent(event = "fullName", label = "FullName", placeholder = "Thea Oudom", formVm = viewModel, onTextChange = { viewModel.updateName(it)})
            Row (modifier = Modifier.fillMaxWidth()){
                Box(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(0.dp, 0.dp, 5.dp, 0.dp)
                ){
                    TextComponent(event ="gender", label = "Gender", placeholder = "Male", formVm = viewModel, onTextChange = {viewModel.updateGender(it)})
                }
                Box(
                    modifier = Modifier
                        .weight(0.6f)
                        .padding(5.dp, 0.dp, 0.dp, 0.dp)
                ){
                    TextComponent(event ="phone", label = "Phone", placeholder = "0884774515", formVm = viewModel, onTextChange = {viewModel.updatePhone(it)})
                }
            }
            TextComponent(event = "address", label = "Address", placeholder = "Prey Veng", formVm = viewModel, onTextChange = {viewModel.updateAddress(it)})
            Button(
                onClick = { /*TODO*/ },modifier = Modifier.fillMaxWidth(),
                enabled = button
            ) {
                Text(text = "Register")
            }
        }
    }
}

@Composable
fun TextComponent(
    placeholder: String,
    label: String,
    onTextChange: (text:String)-> Unit,
    event: String,
    formVm:FormVm
){
    OutlinedTextField(
        value = if (event === "fullName")
            formVm.userData.value.fullName
        else if (event === "gender")
            formVm.userData.value.gender
        else if (event === "phone")
            formVm.userData.value.phone
        else
            formVm.userData.value.address,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 5.dp, 0.dp, 5.dp),

        onValueChange = { newValue ->
            val filteredValue = if (event == "phone") {
                newValue.filter { it.isDigit() }
            } else {
                newValue
            }
            onTextChange(filteredValue)
        },
        placeholder = {
            Text(text = placeholder)
        },
        label = { Text(text = label)},
        keyboardOptions = KeyboardOptions(
            keyboardType = if (event === "phone") KeyboardType.Number else KeyboardType.Text
        ),
    )
}