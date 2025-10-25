package me.jose.alejandro.tellez.prada.mvvm_example


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskRepository {
    // Simulamos una base de datos con una lista mutable
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: Flow<List<Task>> = _tasks.asStateFlow()

    private var nextId = 1

    fun addTask(title: String) {
        val newTask = Task(id = nextId++, title = title)
        _tasks.value = _tasks.value + newTask
    }

    fun toggleTask(taskId: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == taskId) {
                task.copy(isCompleted = !task.isCompleted)
            } else {
                task
            }
        }
    }

    fun deleteTask(taskId: Int) {
        _tasks.value = _tasks.value.filter { it.id != taskId }
    }
}