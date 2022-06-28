package com.example.myapplication.data

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData();

    fun addTask(task: Task){
        taskDao.addTask(task);
    }

    fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    fun deleteAllData(){
        taskDao.deleteAllData()
    }
}