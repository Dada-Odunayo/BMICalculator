package com.example.bmicalculator.ui

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bmicalculator.ValidationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.xml.validation.Validator
import kotlin.math.pow
import kotlin.properties.Delegates

class BMIViewModel:ViewModel() {
    var height by mutableStateOf("")
    var weight by mutableStateOf("")
    var bmi by mutableStateOf("")
    private var bmiValue by Delegates.notNull<Float>()
    lateinit var bmiClass:String
    lateinit var error:String

    fun validateInput():ValidationResult{
        if(height.isEmpty() || weight.isEmpty()){
            return ValidationResult.Invalid("Input fields cannot be empty")
        }
        if(height.toInt()<0||weight.toInt()<0){
            return ValidationResult.Invalid("Weight or Height cannot be negative")

        }
        return if(height.all { it.isDigit() } || weight.all{it.isDigit()}){
            calculateBMI()
            ValidationResult.Valid

        }
        else
        {
            ValidationResult.Invalid("Invalid Entry")
        }

    }
    private fun calculateBMI() {
        val heightInCM = (height.toFloatOrNull() ?: 0f) / 100
        val weightInCM = weight.toFloatOrNull()?:0f
        bmiValue = weightInCM.div((heightInCM.toDouble().pow(2))).toFloat()
        bmi = String.format("%.2f",bmiValue)
        classifyBMI()
       }

    private fun classifyBMI():String{

        if(bmiValue<18.5){
            bmiClass = "Under Weight"
        }
        if(bmiValue>=18.5 && bmiValue<24.9){
            bmiClass = "Healthy Weight"
        }
        if(bmiValue>30){
            bmiClass = "Obese"
        }
        return bmiClass
    }
}
