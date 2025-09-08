// FileName: TaskScreen.kt
package com.rahul.auric.todo.ui.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rahul.auric.todo.data.Task
import com.rahul.auric.todo.ui.tasks.components.AddEditTaskDialog
import com.rahul.auric.todo.ui.tasks.components.TaskItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val incompleteTasks by viewModel.incompleteTasks.collectAsState()
    val completedTasks by viewModel.completedTasks.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Active", "Completed")

    var taskToEdit by remember { mutableStateOf<Task?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("To-Do List") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                taskToEdit = null
                showDialog = true
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Task")
            }
        }
    ) { paddingValues ->

        if (showDialog) {
            AddEditTaskDialog(
                taskToEdit = taskToEdit,
                onDismiss = {
                    showDialog = false
                    taskToEdit = null
                },

                // We now receive the category from the dialog and pass it to the ViewModel
                onConfirm = { title, description, dueDate, category ->
                    if (taskToEdit == null) {
                        viewModel.addTask(
                            title = title,
                            description = description.ifBlank { null },
                            dueDate = dueDate,
                            category = category // Pass the category here
                        )
                    } else {
                        val updatedTask = taskToEdit!!.copy(
                            title = title,
                            description = description.ifBlank { null },
                            dueDate = dueDate,
                            category = category
                        )
                        viewModel.updateTask(updatedTask)
                    }
                    showDialog = false
                    taskToEdit = null
                }
            )
        }

        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            val onEditTask = { task: Task ->
                taskToEdit = task
                showDialog = true
            }

            when (tabIndex) {
                0 -> TaskListContent(
                    tasks = incompleteTasks,
                    emptyListMessage = "No active tasks!\nTap the + button to add one.",
                    viewModel = viewModel,
                    onEditClick = onEditTask
                )
                1 -> TaskListContent(
                    tasks = completedTasks,
                    emptyListMessage = "No tasks completed yet.",
                    viewModel = viewModel,
                    onEditClick = onEditTask
                )
            }
        }
    }
}

@Composable
private fun TaskListContent(
    tasks: List<Task>,
    emptyListMessage: String,
    viewModel: TaskViewModel,
    onEditClick: (Task) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (tasks.isEmpty()) {
            Text(
                text = emptyListMessage,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
                items(tasks, key = { it.id }) { task ->
                    TaskItem(
                        task = task,
                        onTaskCheckedChange = { isChecked ->
                            val updatedTask = task.copy(isCompleted = isChecked)
                            viewModel.updateTask(updatedTask)
                        },
                        onDeleteClick = { viewModel.deleteTask(task) },
                        onEditClick = { onEditClick(task) },
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}