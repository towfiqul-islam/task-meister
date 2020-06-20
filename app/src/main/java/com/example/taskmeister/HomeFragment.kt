package com.example.taskmeister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmeister.adapters.TaskCardAdapter
import com.example.taskmeister.adapters.TaskItemAdapter
import com.example.taskmeister.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.task_card_view.view.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val cardManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val cardAdapter = TaskCardAdapter()

        binding.tasksList.layoutManager = cardManager
        binding.tasksList.adapter = cardAdapter


        return binding.root
    }


}