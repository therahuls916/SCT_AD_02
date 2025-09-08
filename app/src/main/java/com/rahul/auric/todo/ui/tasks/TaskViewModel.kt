// FileName: TaskViewModel.kt
package com.rahul.auric.todo.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.auric.todo.data.Task
import com.rahul.auric.todo.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    /*
    We are transforming the single flow from the
    repository into two separate StateFlows for the UI.
     */
    // A flow for tasks that are not completed.
    val incompleteTasks: StateFlow<List<Task>> = taskRepository.allTasks
        .map { tasks ->
            tasks.filter { !it.isCompleted }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    // A flow for tasks that are completed.
    val completedTasks: StateFlow<List<Task>> = taskRepository.allTasks
        .map { tasks ->
            tasks.filter { it.isCompleted }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )


    fun addTask(title: String, description: String?, dueDate: Long, category: String) {
        viewModelScope.launch {
            val task = Task(
                title = title,
                description = description,
                dueDate = dueDate,
                category = category,
                isCompleted = false // New tasks are always incomplete
            )
            taskRepository.insert(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskRepository.update(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.delete(task)
        }
    }
}