package com.example.week1

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import com.example.week1.domain.*
import java.time.LocalDate
import com.example.week1.domain.mockTasks

class TaskViewModel : ViewModel() {

    var tasks by mutableStateOf(listOf<Task>())
        private set


    init {
        tasks = mockTasks
        //Mockdatalla alustus
    }

    //Lisää task
    fun addTask(task: Task) {
        tasks = addTask(tasks, task)
    }

    //Taskin tilan vaihto
    fun toggleDone(id: Int) {
        tasks = toggleDone(tasks, id)
    }

    //Poista tehtava id:n perusteella
    fun removeTask(id: Int) {
        tasks = removeTask(tasks, id)
    }

    //
    fun filterByDone(done: Boolean) {
        tasks = filterByDone(tasks, done)
    }

    //Järjesta tehtävät
    fun sortByDueDate() {
        tasks = sortByDueDate(tasks)
    }
}