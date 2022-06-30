package com.example.myapplication.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanager.R
import com.example.eventmanager.data.EventViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    private lateinit var mEventViewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val floatingButton = view.findViewById<FloatingActionButton>(R.id.floatingButton)
        floatingButton.setOnClickListener {_ ->
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }
}