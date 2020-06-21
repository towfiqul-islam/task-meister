package com.example.taskmeister

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmeister.adapters.TaskItemAdapter
import com.example.taskmeister.data.Task
import com.example.taskmeister.data.TaskItem
import com.example.taskmeister.databinding.FragmentTasksBinding


class TasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val data = listOf(
            Task("My Tasks",
            listOf(TaskItem("Buy Shoes", true), TaskItem("Run 3 miles"))),
            Task(("Vacation"), listOf(TaskItem("Plan weekend")))
        )


        val binding = FragmentTasksBinding.inflate(inflater, container, false)
        val adapter = TaskItemAdapter(data[0].list, true)
        binding.tasksList2.adapter = adapter

        val layoutManager = LinearLayoutManager(activity)

        binding.tasksList2.layoutManager = layoutManager

        binding.goHome.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_tasksFragment_to_homeFragment)
        }



        return binding.root
    }




}