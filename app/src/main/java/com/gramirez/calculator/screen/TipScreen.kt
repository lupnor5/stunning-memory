package com.gramirez.calculator.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gramirez.calculator.R
import com.gramirez.calculator.components.TipPercentageSelector
import com.gramirez.calculator.viewmodel.TipViewModel


@Preview(showBackground = true)
@Composable
fun TipScreen(
    navController: NavController = rememberNavController(),
    viewModel : TipViewModel = viewModel()
)  {

    val billAmount by viewModel.billAmount.collectAsState()
    val tipPercentage by  viewModel.tipPercentage.collectAsState()
    val customTipAmount by viewModel.customTipAmount.collectAsState()
    val totalToPay by viewModel.totalToPay.collectAsState()


    val isBillValid = (
            billAmount.toDoubleOrNull() != null &&
            billAmount.toDouble() > 0
            )

    val tipOptions = listOf(5, 10, 15, 20)

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.bill)
    )

    Column(
        modifier = Modifier
            .padding(top =30.dp)
            .padding(horizontal = 15.dp)
    ) {
        Text(text = "Total amount")

        TextField(
            value = billAmount,
            onValueChange = viewModel::setBillAmount,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Please select a tip percentage")

        TipPercentageSelector(
            options = tipOptions,
            selected = tipPercentage,
            onSelected = viewModel::setTipPercentage,
            enabled = isBillValid
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Do you want to add a custom tip amount?")

        TextField(
            value = customTipAmount,
            onValueChange = viewModel::setCustomTipAmount,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Total to pay: $totalToPay",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Button(
                onClick = viewModel::reset
            ) {
                Text(text = "Reset")
            }

            Button(
                onClick = {
                    navController.navigate("thankyou")
                }
            ) {
                Text(text = "Pay")
            }

        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        )
        {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.width(300.dp)
            )
        }
    }
}








