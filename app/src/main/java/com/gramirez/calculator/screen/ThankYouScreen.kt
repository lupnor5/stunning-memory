package com.gramirez.calculator.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.gramirez.calculator.viewmodel.TipViewModel


@Preview(showBackground = true)
@Composable
fun ThankYouScreen(
    navController: NavController = rememberNavController(),
    viewModel : TipViewModel = viewModel()
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.thanks)
    )

    val totalToPay by viewModel.totalToPay.collectAsState()


    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text="Thank you for your purchase!!",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text (
            text = "Total paid: $totalToPay",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(15.dp))

        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.width(400.dp)
        )

        Button(
            onClick = {
                viewModel.reset()
                navController.navigate("main")
            }
        ){
            Text(text = "Return to home screen")
        }
    }
}