package com.example.eventmanager.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanager.R
import com.example.eventmanager.data.Event
import com.example.eventmanager.data.EventViewModel

class ListAdapter(): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var eventList = emptyList<Event>()
    private lateinit var mEventViewModel: EventViewModel

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        mEventViewModel = ViewModelProvider(parent.findFragment())[EventViewModel::class.java]
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent,false));
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentEvent = eventList[position]

        holder.itemView.findViewById<TextView>(R.id.eventName_txt).text = currentEvent.name
        holder.itemView.findViewById<TextView>(R.id.eventLocation_txt).text = currentEvent.location
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}