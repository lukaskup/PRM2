package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

// Dao - Data Access Object
@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addTask(task: Task)

    @Query("SELECT * FROM task_table WHERE deadline > strftime('%s','now') || substr(strftime('%f','now'),4) ORDER BY deadline;")
    fun readAllData(): LiveData<List<Task>>

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE from task_table")
    fun deleteAllData()
}