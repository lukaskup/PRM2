package com.example.myapplication.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_list.view.*
import java.util.*

class ListFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel

    private fun getEndOfTheWeek(): Date{
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, Calendar.DATE * -1 + 1)
        calendar.add(Calendar.DATE, 6)
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)

        return calendar.time
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java )
        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer { tasks ->
            adapter.setData(tasks)
            val endOfTheWeek = getEndOfTheWeek()
            val tasksLeft = tasks.filter{task -> task.deadline >= Date() &&  task.deadline <= endOfTheWeek && task.progress < 100}
            view.tasksLeft.text = "Tasks left this week: ${tasksLeft.size}"
        })

        val floatingButton = view.findViewById<FloatingActionButton>(R.id.floatingButton)
        floatingButton.setOnClickListener {_ ->
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }
}