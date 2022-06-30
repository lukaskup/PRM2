package com.example.myapplication.fragments.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eventmanager.data.EventViewModel
import com.example.eventmanager.R
import java.text.SimpleDateFormat
import java.util.*


class AddFragment : Fragment() {

    private lateinit var mEventViewModel: EventViewModel
    private val myCalendar: Calendar = Calendar.getInstance()
    private var deadline: Date? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        val editDate = view.findViewById<EditText>(R.id.eventDate);
        val date =
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel(editDate)
            }

        editDate.setOnClickListener(View.OnClickListener {
            DatePickerDialog(
                view.context,
                date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        })

        view.findViewById<Button>(R.id.saveEvent).setOnClickListener{
            saveData(view)
        }

        return view
    }

    private fun saveData(view: View){
//        val name = view.taskTitle.text.toString()
//        val priority = view.taskPriority.text.toString()
//        val estimate = view.taskEstimate.text.toString()
//
//        if(inputCheck(name) && inputCheck(priority) && inputCheck(estimate) && deadline !== null) {
//            val task = Task(0, name, priority.toInt(), 0, deadline!!, estimate.toInt())
//            mTaskViewModel.addTask(task)
//            Toast.makeText(requireContext(), "Task saved", Toast.LENGTH_SHORT).show()
//
//            findNavController().navigate(R.id.action_addFragment_to_listFragment)
//        } else {
//            Toast.makeText(requireContext(), "Fill out all required fields", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun inputCheck(value: String): Boolean{
        return !(TextUtils.isEmpty(value))
    }

    private fun updateLabel(editText: EditText) {
        val myFormat = "dd/MM/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        editText.setText(dateFormat.format(myCalendar.time))
        deadline = myCalendar.time
    }
}