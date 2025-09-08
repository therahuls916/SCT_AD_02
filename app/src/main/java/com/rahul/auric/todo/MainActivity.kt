// FileName: MainActivity.kt
package com.rahul.auric.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.rahul.auric.todo.ui.tasks.TaskScreen
import com.rahul.auric.todo.ui.tasks.TaskViewModel
import com.rahul.auric.todo.ui.theme.ToDoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Use the by viewModels() delegate to get a lifecycle-aware
    // instance of the TaskViewModel. Hilt provides it automatically.
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // This is where our actual UI will live.
                    TaskScreen(viewModel = taskViewModel)
                }
            }
        }
    }
}