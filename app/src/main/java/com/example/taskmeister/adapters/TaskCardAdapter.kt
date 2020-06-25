package com.example.taskmeister.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmeister.HomeFragmentDirections
import com.example.taskmeister.R
import com.example.taskmeister.database.Task
import com.example.taskmeister.database.TaskHeader


class TaskCardAdapter(val datas: List<TaskHeader> = listOf()) : RecyclerView.Adapter<TaskCardAdapter.ViewHolder>() {

    var data = datas
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    // viewHolder for task card on home screen
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.task_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Assigning card to each position
        val item = data[position]

        // changing card background on home screen
        if (position % 2 == 1) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#363638"))
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#392B85"))
        }

        // setting header text for each card
        holder.taskName.text = item.taskHeader

        // adding recyclerView adapter for each card
        holder.itemLists.adapter = TaskItemAdapter()

        // navigating to taskFragment from card
        holder.cardView.setOnClickListener { view: View ->
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTasksFragment(item.taskHeader))
        }
    }

    // Defining viewHolder: TODO: Requires optimization
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.card_view)
        val taskName: TextView = itemView.findViewById(R.id.task_name)
        val itemLists: RecyclerView = itemView.findViewById(R.id.item_lists)
    }
}

