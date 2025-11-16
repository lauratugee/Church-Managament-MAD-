package com.example.churchmanagementsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.churchmanagementsystem.ui.HomeScreen
import com.example.churchmanagementsystem.ui.LoginScreen
import com.example.churchmanagementsystem.ui.RegistrationScreen
import com.example.churchmanagementsystem.ui.AdminDashboardScreen
import com.example.churchmanagementsystem.ui.treasurer.TreasurerDashboardScreen
import com.example.churchmanagementsystem.ui.group.GroupScreen
import com.example.churchmanagementsystem.ui.mass_schedule.MassScheduleScreen
import com.example.churchmanagementsystem.ui.admin.UpdateMassScheduleScreen
import com.example.churchmanagementsystem.ui.admin.ApproveMemberScreen
import com.example.churchmanagementsystem.ui.admin.AddMassScheduleScreen
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

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                navController = navController,
                onNavigateToRegister = { navController.navigate("register") }
            )

        }
        composable("register") {
            RegistrationScreen(
                navController = navController,
                onNavigateToLogin = { navController.navigate("login") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
                }
            )


        }
        composable("home/{userEmail}") {
            backStackEntry ->
            val email = backStackEntry.arguments?.getString("userEmail") ?: "No email found"

            HomeScreen(
                userEmail = email,
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                }
            )
        }
        composable("admin_dashboard") {
            AdminDashboardScreen(navController)
        }
        composable("create_group") {
            GroupScreen()
        }
        composable("update_mass_schedule") {
            MassScheduleScreen()
        }
        composable("approve_members") {
            ApproveMembersScreen(navController)
        }
        composable ("treasurer_dashboard") {
            TreasurerDashboardScreen(navController = navController)
        }
    }
}


@Composable
fun UpdateMassScheduleScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text("Update Mass Schedule Screen")
    }
}

@Composable
fun ApproveMembersScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Approve Members Screen")
    }
}

@Composable
fun TreasurerDashboardScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Treasurer Dashboard Screen")
    }
}







