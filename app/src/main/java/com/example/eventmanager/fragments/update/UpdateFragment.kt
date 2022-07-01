package com.example.eventmanager.fragments.update

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
import com.example.eventmanager.R
import com.example.eventmanager.data.Event
import com.example.eventmanager.data.EventViewModel
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.text.SimpleDateFormat
import java.util.*

class UpdateFragment : Fragment() {
    private val myCalendar: Calendar = Calendar.getInstance()
    private val args by navArgs<UpdateFragmentArgs>()
    private var eventDate: Date? = null
    private lateinit var mEventViewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_update, container, false)
        mEventViewModel = ViewModelProvider(this)[EventViewModel::class.java]
        view.eventUpdateName.setText(args.currentEvent.name)
        view.eventUpdateImage.setText(args.currentEvent.imageUrl)
        view.eventUpdateLocation.setText(args.currentEvent.location)
        eventDate = args.currentEvent.date
        val editDate = view.findViewById<EditText>(R.id.eventUpdateDate)

        updateLabel(editDate, true)

        val date =
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel(editDate, false)

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

        view.findViewById<Button>(R.id.updateTask).setOnClickListener{
            updateItem(view)
        }

        return view
    }
    private fun updateLabel(editText: EditText, skipUpdateDate: Boolean) {
        val myFormat = "dd/MM/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        if(skipUpdateDate) {
            editText.setText(dateFormat.format(eventDate!!))
        } else {
            editText.setText(dateFormat.format(myCalendar.time))
            eventDate = myCalendar.time
        }
    }

    private fun updateItem(view: View) {
        val eventName = view.eventUpdateName.text.toString()
        val eventImageUrl = view.eventUpdateImage.text.toString()
        val eventLocation = view.eventUpdateLocation.text.toString()

        if(inputCheck(eventName) && inputCheck(eventImageUrl) && inputCheck(eventLocation) && eventDate !== null) {
            val event = Event(args.currentEvent.id, eventName, eventLocation,eventImageUrl, eventDate!!)
            mEventViewModel.updateEvent(event)
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