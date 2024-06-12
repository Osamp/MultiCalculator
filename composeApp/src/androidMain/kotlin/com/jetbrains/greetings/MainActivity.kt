package com.jetbrains.greetings

import App
import Calculator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun App() {
    CalcView()
}

@Composable
fun CalcView(){

}

@Composable
fun CalcRow(){

}

@Composable
fun CalcDisplay(display: MutableState<String>){
    Text(
        text = display.value,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth()
    )

}

@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>){
    Button(
        onClick = { display.value += number.toString() },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString())

    }
}

@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>){
    Button(
        onClick = { display.value += operation },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(operation)
    }
}

@Composable
fun CalcEqualsButton(display: MutableState<String>, calculator: Calculator){
    Button(
        onClick = {
            try {
                if (display.value != "0") {
                    val expression = display.value
                    val parts = expression.split("\\+|\\-|\\*|\\/".toRegex())
                    if (parts.size == 2) {
                        val operand1 = parts[0].toInt()
                        val operand2 = parts[1].toInt()
                        val operation = expression.replace("\\d+".toRegex(), "").trim()

                        val result = when (operation) {
                            "+" -> calculator.Add(operand1, operand2)
                            "-" -> calculator.Subtract(operand1, operand2)
                            "*" -> calculator.Multiply(operand1, operand2)
                            "/" -> calculator.Divide(operand1, operand2)
                            else -> throw IllegalArgumentException("Unknown operator")
                        }
                        display.value = result.toString()
                    } else {
                        display.value = "Error"
                    }
                } else {
                    display.value = "0"
                }
            } catch (e: Exception) {
                display.value = "0"
            }
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=")
    }

}


