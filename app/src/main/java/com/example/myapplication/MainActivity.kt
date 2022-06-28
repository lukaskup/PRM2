package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.data.Task
import com.example.myapplication.data.TaskViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java )

        setupActionBarWithNavController(findNavController(R.id.fragment))

//        mTaskViewModel.deleteAllData()

        //add example data
        val tasks: Array<Task> = arrayOf(
            Task(0, "Create example data", 1, 20, Date(2022, 4, 1), 60),
            Task(0, "Add delete functionality", 6, 0, Date(2022, 4, 20), 30),
            Task(0, "Initialize viewModelProvider", 8, 0, Date(2022, 5, 8), 15),
            Task(0, "Make room database builder", 2, 100, Date(2022, 5, 8), 120),
            Task(0, "Download SqLite database viewer", 4, 30, Date(2022, 5, 8), 45),
            Task(0, "Add Tasks left this week", 5, 70, Date(2022, 5, 12), 180),
            Task(0, "This is an example task", 10, 90, Date(2022, 5, 28), 360)
        )

        tasks.forEach { task -> mTaskViewModel.addTask(task) }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}