package com.example.eventmanager.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel(application: Application): AndroidViewModel(application) {
    private val repository: EventRepository

    init {
        repository = EventRepository()
    }

    fun readAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.readAllData()
        }
    }

    fun addTask(event: Event){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEvent(event)
        }
    }

    fun deleteTask(event: Event){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEvent(event)
        }
    }

    fun updateTask(event: Event){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEvent(event)
        }
    }

    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }
}