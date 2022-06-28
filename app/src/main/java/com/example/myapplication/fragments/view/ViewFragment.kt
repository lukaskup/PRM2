package com.example.myapplication.fragments.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.data.TaskViewModel
import kotlinx.android.synthetic.main.fragment_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class ViewFragment: Fragment() {
    private lateinit var mTaskViewModel: TaskViewModel
    private val args by navArgs<ViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        setHasOptionsMenu(true)

        view.taskViewTitle.text = args.currentTask.title
        view.taskViewPriority.text = "Priority: ${args.currentTask.priority}"
        view.taskViewEstimate.text = "Estimate (minutes): ${args.currentTask.estimateTimeMinutes} m"
        view.taskViewDeadline.text = "Due to: ${args.currentTask.deadline}"
        view.taskViewProgress.text = "${args.currentTask.progress}%"
        view.circularProgressIndicator.progress = args.currentTask.progress

        val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.US)
        view.taskViewDeadline.text = dateFormat.format(args.currentTask.deadline)


        view.findViewById<Button>(R.id.shareTaskProgressButton).setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, args.currentTask.toString())
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.view_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.view_menu) {
            deleteTask()
        }
        if(item.itemId == R.id.menu_edit) {
            val action = ViewFragmentDirections.actionViewFragmentToUpdateFragment(args.currentTask)
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yup!") { _, _ ->
            mTaskViewModel.deleteTask(args.currentTask)
            Toast.makeText(requireContext(), "Successfully deleted task ${args.currentTask.id}!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_viewFragment_to_listFragment)
        }
        builder.setNegativeButton("Nope!") { _, _ -> }
        builder.setTitle("Delete task")
        builder.setMessage("Are you sure you want to delete this task?")
        builder.create().show()
    }
}