package com.julianmartinez.country_code_picker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.julianmartinez.ccp.components.CountryCodePicker
import com.julianmartinez.ccp.components.getErrorStatus
import com.julianmartinez.ccp.components.getFullPhoneNumber
import com.julianmartinez.ccp.components.getOnlyPhoneCode
import com.julianmartinez.ccp.components.getOnlyPhoneNumber
import com.julianmartinez.ccp.components.isPhoneNumber
import com.julianmartinez.country_code_picker.ui.theme.Country_code_pickerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Country_code_pickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = { TopAppBar(title = { Text(text = "Togisoft") }) })
                    {
                            top ->
                        top.calculateTopPadding()
                        Surface(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .verticalScroll(rememberScrollState())
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                val phoneNumber = rememberSaveable { mutableStateOf("") }
                                val fullPhoneNumber = rememberSaveable { mutableStateOf("") }
                                val onlyPhoneNumber = rememberSaveable { mutableStateOf("") }
                                val onlyPhoneCode = rememberSaveable { mutableStateOf("") }

                                CountryCodePicker(
                                    text = phoneNumber.value,
                                    onValueChange = { phoneNumber.value = it },
                                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                                    bottomStyle = false,
                                    shape = RoundedCornerShape(24.dp)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Button(onClick = {
                                    if (isPhoneNumber()) {
                                        fullPhoneNumber.value = getFullPhoneNumber()
                                        onlyPhoneNumber.value = getOnlyPhoneNumber()
                                        onlyPhoneCode.value = getOnlyPhoneCode()
                                    } else {
                                        fullPhoneNumber.value = "Error"
                                        onlyPhoneNumber.value = "Error"
                                        onlyPhoneCode.value = "Error"
                                    }
                                }) {
                                    Text(text = "Check")
                                }

                                Text(
                                    text = "Full Phone Number: ${fullPhoneNumber.value}",
                                    color = if (getErrorStatus()) Color.Red else Color.Green
                                )

                                Text(
                                    text = "Only Phone Number: ${onlyPhoneNumber.value}",
                                    color = if (getErrorStatus()) Color.Red else Color.Green
                                )

                                Text(
                                    text = "Only Phone Code: ${onlyPhoneCode.value}",
                                    color = if (getErrorStatus()) Color.Red else Color.Green
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Country_code_pickerTheme {
        Greeting("Android")
    }
}