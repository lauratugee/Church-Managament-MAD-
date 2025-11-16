package com.example.churchmanagementsystem.ui.group


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.churchmanagementsystem.ChurchManagementApplication

@Composable
fun GroupScreen() {
    val context=LocalContext.current
    val application=context.applicationContext as ChurchManagementApplication

    val groupViewModel: GroupViewModel = viewModel(
        factory = GroupViewModelFactory(application.repository)
    )
    val groups by groupViewModel.groups.collectAsState()

    var groupName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text="Group list",style=androidx.compose.material3.MaterialTheme.typography.bodyMedium)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)

        ){
            OutlinedTextField(
                value = groupName,
                onValueChange = { groupName = it },
                label = { Text("Group name")},
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    groupViewModel.addGroup(groupName)
                    groupName = ""
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }

        }
        LazyColumn {
            items(groups) { group ->
                Text(
                    text = group.name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

        }
    }

}