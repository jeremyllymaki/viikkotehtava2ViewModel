package com.example.week1


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week1.domain.Priority
import com.example.week1.domain.Task
import java.time.LocalDate
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton




@Composable
fun HomeScreen(taskViewModel: TaskViewModel = viewModel(), modifier: Modifier = Modifier) {

    var filterDone by remember { mutableStateOf<Boolean?>(null) }
    var newTaskTitle by remember { mutableStateOf("") }

    // Näytettävä lista riippuu filterDone-arvosta
    val visibleList = when (filterDone) {
        null -> taskViewModel.tasks
        else -> taskViewModel.tasks.filter {

            it.done == filterDone
        }
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Task List", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Uuden tehtävän lisääminen
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("New Task") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (newTaskTitle.isNotBlank()) {
                    val nextId = (taskViewModel.tasks.maxOfOrNull { it.id } ?: 0) + 1
                    taskViewModel.addTask(
                        Task(
                            id = nextId,
                            title = newTaskTitle,
                            description = "",
                            priority = Priority.LOW,
                            dueDate = LocalDate.now(),
                            done = false
                        )
                    )
                    newTaskTitle = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filter ja Sort napit
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = {
                filterDone = when (filterDone) {
                    null -> false
                    false -> true
                    true -> null
                }
            }) {
                Text(
                    when (filterDone) {
                        null -> "Filter: All"
                        false -> "Filter: Todo"
                        true -> "Filter: Done"
                    }
                )
            }

            Button(onClick = { taskViewModel.sortByDueDate() }) {
                Text("Sort")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista tehtävistä
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(visibleList) { task ->
                TaskRow(
                    task = task,
                    onToggleDone = { taskViewModel.toggleDone(task.id) },
                    onRemove = { taskViewModel.removeTask(task.id) }
                )
            }
        }
    }
}



@Composable
fun TaskRow(task: Task, onToggleDone: () -> Unit, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Checkbox(checked = task.done, onCheckedChange = { onToggleDone() })
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(task.title)
                Text("Due: ${task.dueDate}", style = MaterialTheme.typography.bodySmall)
            }
        }
        IconButton(onClick = onRemove) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Remove task"
            )
        }
    }
}