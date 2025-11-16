package com.example.churchmanagementsystem.ui.admin

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.churchmanagementsystem.models.User
import com.example.churchmanagementsystem.viewmodel.AdminViewModel
import com.example.churchmanagementsystem.util.DataState
import com.example.churchmanagementsystem.api.RetrofitInstance
import com.example.churchmanagementsystem.repository.AdminRepository
import com.example.churchmanagementsystem.viewmodel.AdminViewModelFactory



@Composable
fun ApproveMemberScreen(navController: NavController) {
    val apiService = RetrofitInstance.api
    val adminRepository = AdminRepository(apiService)
    val factory= AdminViewModelFactory(adminRepository)
    val adminViewModel: AdminViewModel = viewModel(factory = factory)

    val pendingMembers by adminViewModel.pendingMembers.collectAsState()
    val approvalState by adminViewModel.approvalState.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(approvalState) {
        when (val state = approvalState) {
            is DataState.Success -> {
                Toast.makeText(context, "Member approval successful!", Toast.LENGTH_SHORT).show()
                adminViewModel.resetApprovalState()
            }
            is DataState.Error -> {
                Toast.makeText(context, "Member approval failed: ${state.message}", Toast.LENGTH_SHORT).show()
                adminViewModel.resetApprovalState()
            }
            else -> {

            }

        }
    }




    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Approve Members") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp()}){
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }

                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            when (val state=pendingMembers) {
                is DataState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is DataState.Success -> {
                    val pendingMembers = state.data
                    if (pendingMembers.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No pending members")
                        }
                    } else {
                        LazyColumn(
                            contentPadding = innerPadding,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(pendingMembers, key = { it.id }) { member ->
                                MemberApprovalCard(
                                    member = member,
                                    onApprove = { adminViewModel.approveMember(member.id) },
                                    onDecline = { adminViewModel.declineMember(member.id) }
                                )
                            }
                        }
                    }
                }

                is DataState.Error -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Error: ${state.message}")
                    }
                }

                is DataState.Idle -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))


                }

            }
        }
    }
}
@Composable
private fun MemberApprovalCard(
    member: User,
    onApprove: () -> Unit,
    onDecline: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Name: ${member.firstName} ${member.lastName}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Email: ${member.email}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(onClick = onDecline) {
                    Text("Decline")

                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onApprove) {
                    Text("Approve")
                }

            }
        }
    }
}










