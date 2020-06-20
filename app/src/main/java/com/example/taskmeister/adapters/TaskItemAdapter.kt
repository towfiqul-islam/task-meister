package com.example.taskmeister.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmeister.R
import com.example.taskmeister.data.TaskItem

class TaskItemAdapter(val data: List<TaskItem>) : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.task_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.itemName.text = item.name
        holder.checkBox.isChecked = item.isCompleted

        changeItemView(item, holder.checkBox, holder.itemName)

        holder.itemName.setOnClickListener {
            toggleTaskItem(holder.checkBox, item)
            changeItemView(item, holder.checkBox, holder.itemName)
        }

        holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            item.isCompleted = holder.checkBox.isChecked
            changeItemView(item, holder.checkBox, holder.itemName)
        }


    }

    private fun toggleTaskItem(checkBox: CheckBox, item: TaskItem) {
        if (checkBox.isChecked) {
            item.isCompleted = false
            checkBox.isChecked = item.isCompleted

        } else {
            item.isCompleted = true
            checkBox.isChecked = item.isCompleted

        }
    }

    private fun changeItemView(item: TaskItem, checkBox: CheckBox, itemName: TextView) {
        if (item.isCompleted) {
            itemName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemName.setTextColor(Color.parseColor("#DD9091"))
            checkBox.visibility = View.INVISIBLE

        } else {
            itemName.paintFlags = 0
            itemName.setTextColor(Color.parseColor("#FFE1DF"))
            checkBox.visibility = View.VISIBLE
        }
    }




    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.task_item)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox_done)
    }
}