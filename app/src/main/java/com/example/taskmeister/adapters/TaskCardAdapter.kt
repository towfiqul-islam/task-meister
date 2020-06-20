package com.example.taskmeister.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.getColor
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmeister.MainActivity
import com.example.taskmeister.R
import com.example.taskmeister.data.Task
import com.example.taskmeister.data.TaskItem
import kotlinx.android.synthetic.main.task_card_view.view.*

class TaskCardAdapter : RecyclerView.Adapter<TaskCardAdapter.ViewHolder>() {
    val data = listOf(Task("My Tasks",
        listOf(TaskItem("Buy Shoes", true), TaskItem("Run 3 miles"))),
        Task(("Vacation"), listOf(TaskItem("Plan weekend")))
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.task_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        if (position % 2 == 1) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#DF3435"))
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#392B85"))
        }

        holder.taskName.text = item.taskHeader
        holder.itemLists.adapter = TaskItemAdapter(item.list)

        holder.cardView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_tasksFragment)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.card_view)
        val taskName: TextView = itemView.findViewById(R.id.task_name)
        val itemLists: RecyclerView = itemView.findViewById(R.id.item_lists)
    }
}

