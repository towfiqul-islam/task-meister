package com.example.taskmeister

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmeister.adapters.TaskItemAdapter
import com.example.taskmeister.database.Task
import com.example.taskmeister.database.TaskDatabase
import com.example.taskmeister.databinding.FragmentTasksBinding
import kotlinx.coroutines.NonCancellable.cancel


class TasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTasksBinding.inflate(inflater, container, false)

        // getting arguments from safe args bundle
        val arguments = TasksFragmentArgs.fromBundle(requireArguments())

        // Application context
        val application = requireNotNull(this.activity).application

        // Initializing Database
        val dataSource = TaskDatabase.getInstance(application).taskDao

        // Initialize TaskViewModelFactory
        val viewModelFactory =
            TaskViewModelFactory(dataSource, application, header_id = arguments.headerId)

        // Initialize TaskViewModel
        val taskViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            TaskViewModel::class.java
        )

//        binding.lifecycleOwner = this

        val manager = LinearLayoutManager(activity)
        binding.tasksList2.layoutManager = manager

        binding.taskTitle.text = arguments.headerName


        val adapter = TaskItemAdapter(
            flag = true,
            viewModel = taskViewModel
        ) // flag to inflate different layout
        binding.tasksList2.adapter = adapter

        val progressBar = binding.progressBar
        val subTitle = binding.taskLeftText

        taskViewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it

                setProgress(it, progressBar)
                setTasksLeft(it, subTitle)
            }
        })

        binding.goHome.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_tasksFragment_to_homeFragment)
        }


        binding.addNewItem.setOnClickListener { view: View ->
            val taskText = binding.addNewTaskText.text.toString()
            val headerId = arguments.headerId

            if (taskText.isNotEmpty()) {
                taskViewModel.onAddTask(headerId, taskText)
            }


            binding.addNewTaskText.text.clear()
        }

        binding.deleteList.setOnClickListener { view: View ->

            activity?.let { AlertDialog.Builder(it)

                .setMessage(R.string.dialog_msg)
                    .setPositiveButton(R.string.dialog_yes
                    ) { dialog, id ->
                        taskViewModel.onDelete(arguments.headerId)
                        taskViewModel.onDeleteHeader(arguments.headerId)

                        view.findNavController()
                            .navigate(TasksFragmentDirections.actionTasksFragmentToHomeFragment())
                    }
                .setNegativeButton(R.string.dialog_no
                ) { dialog, id ->
                    // nothing happens
                }.show()
            }





        }


        return binding.root
    }

    private fun setTasksLeft(tasks: List<Task>, subTitle: TextView) {
        var count = 0;
        val totalItems = tasks.size

        tasks.forEach { task ->
            if (task.isCompleted) {
                count++;
            }
        }

        val itemLeft = (totalItems - count).toString()

        if (itemLeft.toInt() == 1) {
            subTitle.text = "$itemLeft task left"
        } else {
            subTitle.text = "$itemLeft tasks left"
        }
    }

    private fun setProgress(tasks: List<Task>, progressBar: ProgressBar) {
        var count = 0.toDouble()
        var totalItems = tasks.size

        tasks.forEach {task ->
            if (task.isCompleted) {
                count++
            }
        }
        val progress = ((count / totalItems) * 100).toInt()
        progressBar.progress = progress
    }

}

class FireMissilesDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.dialog_msg)
                .setPositiveButton(R.string.dialog_yes,
                    DialogInterface.OnClickListener { dialog, id ->
                        // FIRE ZE MISSILES!
                    })
                .setNegativeButton(R.string.dialog_no,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

