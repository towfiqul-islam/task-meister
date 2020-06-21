package com.example.taskmeister.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.widget.CompoundButtonCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmeister.R
import com.example.taskmeister.data.TaskItem

class TaskItemAdapter(val data: List<TaskItem>, val flag: Boolean = false) :
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if (flag) {
            val layoutInflater1 = LayoutInflater.from(parent.context)
            val view2 = layoutInflater1.inflate(R.layout.task_item_view_2, parent, false)
            return ViewHolder(view2)
        } else {
            val layoutInflater2 = LayoutInflater.from(parent.context)
            val view1 = layoutInflater2.inflate(R.layout.task_item_view, parent, false)
            return ViewHolder(view1)
        }

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]


        holder.itemName2?.text = item.name
        holder.checkBox2?.isChecked = item.isCompleted

        holder.itemName?.text = item.name
        holder.checkBox?.isChecked = item.isCompleted


        if (flag) {
            changeItemView(item, holder.checkBox2!!, holder.itemName2!!, flag)
        } else {
            changeItemView(item, holder.checkBox!!, holder.itemName!!, flag)
        }

        if (flag) {
            holder.itemName2?.setOnClickListener {
                toggleTaskItem(holder.checkBox2!!, item)
                changeItemView(item, holder.checkBox2!!, holder.itemName2, flag)

            }

            holder.checkBox2?.setOnCheckedChangeListener { buttonView, isChecked ->

                item.isCompleted = holder.checkBox2.isChecked
                changeItemView(item, holder.checkBox2, holder.itemName2!!, flag)
            }
        } else {
            holder.itemName?.setOnClickListener {
                toggleTaskItem(holder.checkBox!!, item)
                changeItemView(item, holder.checkBox!!, holder.itemName, flag)

            }

            holder.checkBox?.setOnCheckedChangeListener { buttonView, isChecked ->

                item.isCompleted = holder.checkBox.isChecked
                changeItemView(item, holder.checkBox, holder.itemName!!, flag)
            }
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

    private fun changeItemView(item: TaskItem, checkBox: CheckBox, itemName: TextView, flag: Boolean) {
        if (item.isCompleted && !flag) {
            itemName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemName.setTextColor(Color.parseColor("#DF3435"))
            checkBox.visibility = View.INVISIBLE

        } else if (item.isCompleted && flag) {
            itemName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemName.setTextColor(Color.parseColor("#363638"))
            checkBox.visibility = View.INVISIBLE
        }
        else if (!item.isCompleted && flag) {
            itemName.paintFlags = 0
            itemName.setTextColor(Color.parseColor("#363638"))
            checkBox.visibility = View.VISIBLE
        }

        else {
            itemName.paintFlags = 0
            itemName.setTextColor(Color.parseColor("#FFE1DF"))
            checkBox.visibility = View.VISIBLE
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView? = itemView.findViewById(R.id.task_item)
        val checkBox: CheckBox? = itemView.findViewById(R.id.checkbox_done)

        val itemName2: TextView? = itemView.findViewById(R.id.task_item_2)
        val checkBox2: CheckBox? = itemView.findViewById(R.id.checkbox_done_2)
    }
}