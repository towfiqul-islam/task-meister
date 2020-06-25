package com.example.taskmeister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.taskmeister.database.TaskDatabase
import com.example.taskmeister.databinding.FragmentCreateListBinding


class CreateListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentCreateListBinding.inflate(inflater, container, false)

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

        // Navigating to home
        binding.goHome.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_createListFragment_to_homeFragment)
        }

        // Creating a new list
        binding.saveList.setOnClickListener { view: View ->
            val taskHeader = binding.addTaskHeader.text.toString()

            taskViewModel.onAddNewHeader(taskHeader)

            view.findNavController().navigate(
                CreateListFragmentDirections.actionCreateListFragmentToTasksFragment(taskHeader)
            )
        }



        return binding.root
    }


}