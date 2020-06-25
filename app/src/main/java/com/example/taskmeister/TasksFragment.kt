package com.example.taskmeister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmeister.adapters.TaskItemAdapter
import com.example.taskmeister.database.TaskDatabase
import com.example.taskmeister.databinding.FragmentTasksBinding


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
        val viewModelFactory = TaskViewModelFactory(dataSource, application)

        // Initialize TaskViewModel
        val taskViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            TaskViewModel::class.java
        )

//        binding.lifecycleOwner = this

        val manager = LinearLayoutManager(activity)
        binding.tasksList2.layoutManager = manager

        val adapter =  TaskItemAdapter(flag=true) // flag to inflate different layout
        binding.tasksList2.adapter = adapter

        // setting task header
        binding.taskTitle.text = arguments.taskHeader

        // Navigating to home
        binding.goHome.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_tasksFragment_to_homeFragment)
        }

        // Adding a new task item
        binding.addNewItem.setOnClickListener { view: View ->
          // TODO: implementation yet to be done
        }





        return binding.root
    }





}

