package com.example.myapplication.fragments.update

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.data.Task
import com.example.myapplication.data.TaskViewModel
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.text.SimpleDateFormat
import java.util.*

class UpdateFragment : Fragment() {
    private val myCalendar: Calendar = Calendar.getInstance()
    private val args by navArgs<UpdateFragmentArgs>()
    private var deadline: Date? = null
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_update, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        view.taskUpdateTitle.setText(args.currentTask.title)
        view.taskUpdatePriority.setText(args.currentTask.priority.toString())
        view.taskUpdateProgress.setText(args.currentTask.progress.toString())
        view.taskUpdateEstimate.setText(args.currentTask.estimateTimeMinutes.toString())
        deadline = args.currentTask.deadline
        val editText = view.findViewById<EditText>(R.id.taskUpdateDeadline)

        updateLabel(editText, true)

        val date =
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel(editText, false)

            }

        editText.setOnClickListener(View.OnClickListener {
            DatePickerDialog(
                view.context,
                date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        })

        view.findViewById<Button>(R.id.updateTask).setOnClickListener{
            updateItem(view)
        }

        return view
    }
    private fun updateLabel(editText: EditText, skipUpdateDate: Boolean) {
        val myFormat = "dd/MM/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        if(skipUpdateDate) {
            editText.setText(dateFormat.format(deadline!!))
        } else {
            editText.setText(dateFormat.format(myCalendar.time))
            deadline = myCalendar.time
        }
    }

    private fun updateItem(view: View) {
        val taskTitle = view.taskUpdateTitle.text.toString()
        val priority = view.taskUpdatePriority.text.toString()
        val progress = view.taskUpdateProgress.text.toString()
        val estimate= view.taskUpdateEstimate.text.toString()

        if(inputCheck(taskTitle) && inputCheck(priority) && inputCheck(estimate) && deadline !== null) {
            val task = Task(args.currentTask.id, taskTitle, priority.toInt(), progress.toInt(), deadline!!, estimate.toInt())
            mTaskViewModel.updateTask(task)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
        }

    }
    private fun inputCheck(value: String): Boolean{
        return !(TextUtils.isEmpty(value))
    }
}