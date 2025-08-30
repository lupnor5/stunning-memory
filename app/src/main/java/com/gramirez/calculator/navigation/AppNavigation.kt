package com.gramirez.calculator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gramirez.calculator.screen.ThankYouScreen
import com.gramirez.calculator.screen.TipScreen
import com.gramirez.calculator.viewmodel.TipViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: TipViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "main"

    ) {
        composable(route = "main") {
            TipScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "thankyou") {
            ThankYouScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}