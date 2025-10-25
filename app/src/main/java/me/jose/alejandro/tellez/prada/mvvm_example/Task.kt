package me.jose.alejandro.tellez.prada.mvvm_example

data class Task(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false
)