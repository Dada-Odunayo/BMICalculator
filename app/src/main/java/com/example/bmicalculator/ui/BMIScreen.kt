package com.example.bmicalculator.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmicalculator.ValidationResult

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(viewModel: BMIViewModel) {
    val focusManager = LocalFocusManager.current
   val keyboardController = LocalSoftwareKeyboardController.current


    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 30.dp, bottom = 10.dp),
    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
       

            Text(
                text = "Height(cm)",
                modifier = Modifier,
                style = MaterialTheme.typography.h5
            )
            TextField(
                value =viewModel.height ,
                onValueChange = {
                    viewModel.height = it
                },
                placeholder = {Text("Enter Height in Kg")},
                modifier = androidx.compose.ui.Modifier,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),

            )
        Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Weight(kg)",
                modifier = androidx.compose.ui.Modifier,
                style = MaterialTheme.typography.h5

            )
            TextField(
                value =viewModel.weight ,
                onValueChange = {
                    viewModel.weight = it
                },
                modifier = androidx.compose.ui.Modifier,
                maxLines = 1,
                placeholder = {Text("Enter Weight in Kg")},
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                })
            )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            viewModel.validateInput()
                }
        ) {
            Text(
                text = "Calculate BMI",
                textAlign = TextAlign.Center
            )
        }


      
        Spacer(modifier = Modifier.height(16.dp))
        if(viewModel.bmi.isNotEmpty()){
            Text(
                text = "Your BMI is ${viewModel.bmi}",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.error
            )
            Text(text = "You weight is classified as ${viewModel.bmiClass}",
            style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.error
                )
        }
        //Text(text = viewModel.error)

        }
    }

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    val bmiViewModel:BMIViewModel = viewModel()
    MainScreen(viewModel = bmiViewModel)
}