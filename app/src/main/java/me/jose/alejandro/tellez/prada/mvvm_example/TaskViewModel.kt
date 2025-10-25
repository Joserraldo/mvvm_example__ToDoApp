package me.jose.alejandro.tellez.prada.mvvm_example


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Estado de la UI
data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false
)

class TaskViewModel(
    private val repository: TaskRepository = TaskRepository()
) : ViewModel() {

    // Estado observable para la UI
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            repository.tasks.collect { tasks ->
                _uiState.value = _uiState.value.copy(tasks = tasks)
            }
        }
    }

    // Funciones que la UI puede llamar
    fun addTask(title: String) {
        if (title.isNotBlank()) {
            repository.addTask(title)
        }
    }

    fun toggleTask(taskId: Int) {
        repository.toggleTask(taskId)
    }

    fun deleteTask(taskId: Int) {
        repository.deleteTask(taskId)
    }
}
