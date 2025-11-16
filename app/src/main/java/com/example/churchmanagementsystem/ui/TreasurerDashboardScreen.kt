package com.example.churchmanagementsystem.ui.treasurer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.churchmanagementsystem.ChurchManagementApplication
import com.example.churchmanagementsystem.viewmodel.TreasurerViewModel
import com.example.churchmanagementsystem.viewmodel.TreasurerViewModelFactory



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreasurerDashboardScreen(navController: NavController){
    val context= LocalContext.current
    val application=context.applicationContext as ChurchManagementApplication
    val treasurerViewModel: TreasurerViewModel = viewModel(
        factory = TreasurerViewModelFactory(application.treasurerRepository)
    )
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Tithe records", "Fundraiser records")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Treasurer Dashboard") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
            when (selectedTabIndex) {
                0 -> TitheRecordsScreen(treasurerViewModel)
                1 -> FundraiserRecordsScreen(treasurerViewModel)
            }
        }
    }
}



@Composable
fun TitheRecordsScreen(treasurerViewModel: TreasurerViewModel) {
    var massName by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    var message by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ){
        Text("New tithe records",style=MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = massName,
            onValueChange = { massName = it },
            label = { Text("Mass Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                treasurerViewModel.addTithe(
                    massName = massName,
                    amount = amount,
                    date = date,
                    notes = notes.takeIf { it.isNotBlank() }
                ) {
                    isSuccess ->
                    if (isSuccess) {
                        message = "Tithe record added successfully"
                        massName = ""
                        amount = ""
                        date = ""
                        notes = ""
                    } else {
                        message = "Failed to add tithe record"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Tithe Record")
        }
                message?.let{
                    Text(
                        text=it,
                        color=MaterialTheme.colorScheme.error,
                        style=MaterialTheme.typography.bodySmall,
                    )
                }
    }
}
@Composable
fun FundraiserRecordsScreen(treasurerViewModel: TreasurerViewModel) {
    var fundraiserName by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var message by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("New fundraiser records", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = fundraiserName,
            onValueChange = { fundraiserName = it },
            label = { Text("Fundraiser Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Fundraiser Record")
        }
        message?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}





































