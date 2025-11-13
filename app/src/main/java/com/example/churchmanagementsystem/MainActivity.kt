package com.example.churchmanagementsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.churchmanagementsystem.ui.HomeScreen
import com.example.churchmanagementsystem.ui.LoginScreen
import com.example.churchmanagementsystem.ui.RegistrationScreen
import com.example.churchmanagementsystem.ui.AdminDashboardScreen
import com.example.churchmanagementsystem.ui.theme.ChurchManagementSystemTheme
import com.example.churchmanagementsystem.viewmodel.AuthViewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChurchManagementSystemTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation()

                }
            }
        }
    }
}

@Composable
fun AppNavigation () {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                navController = navController,
                onNavigateToRegister = {
                    navController.navigate("register")

                }
            )
        }
        composable("register") {
            RegistrationScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
        composable("home/{userEmail}") {
            backStackEntry ->
            val email = backStackEntry.arguments?.getString("userEmail") ?: "No email found"

            HomeScreen(
                userEmail = email,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("home/$email") { inclusive = true }
                    }
                }
            )
        }
        composable("admin_dashboard") {
            AdminDashboardScreen(navController)
        }
        composable("create_group") {
            CreateGroupScreen(navController)
        }
        composable("update_mass_schedule") {
            UpdateMassScheduleScreen(navController)
        }
        composable("approve_members") {
            ApproveMembersScreen(navController)
        }
    }
        }
    }



