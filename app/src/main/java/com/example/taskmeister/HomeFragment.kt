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
import com.example.taskmeister.adapters.TaskCardAdapter
import com.example.taskmeister.database.TaskDatabase
import com.example.taskmeister.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

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



        val manager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = TaskCardAdapter(viewModel = taskViewModel)

        binding.tasksList.layoutManager = manager
        binding.tasksList.adapter = adapter

        taskViewModel.taskHeads.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })


        binding.createListBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCreateListFragment())
        }








        return binding.root
    }


}