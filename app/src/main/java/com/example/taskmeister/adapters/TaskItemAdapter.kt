package com.example.taskmeister.adapters

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmeister.R
import com.example.taskmeister.database.Task

class TaskItemAdapter(val datas: List<Task> = listOf(), val flag: Boolean = false):
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    var data = datas
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // Inflating different layout based on homeFragment and taskFragment
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
        // Assigning each taskItem to position
        val item = data[position]

        // Setting taskItem view based on homeFragment and taskFragment start -->
        holder.itemName2?.text = item.itemName
        holder.checkBox2?.isChecked = item.isCompleted

        holder.itemName?.text = item.itemName
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
        // Setting taskItem view based on homeFragment and taskFragment end <--

    }



    // Method to toggle between completed and non-completed items
    private fun toggleTaskItem(checkBox: CheckBox, item: Task) {
        if (checkBox.isChecked) {
            item.isCompleted = false
            checkBox.isChecked = item.isCompleted

        } else {
            item.isCompleted = true
            checkBox.isChecked = item.isCompleted

        }

    }

    // Method to change the UI of completed and non-completed tasks
    private fun changeItemView(
        item: Task,
        checkBox: CheckBox,
        itemName: TextView,
        flag: Boolean
    ) {
        if (item.isCompleted && !flag) {
            itemName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemName.setTextColor(Color.parseColor("#DF3435"))
            checkBox.visibility = View.INVISIBLE

        } else if (item.isCompleted && flag) {
            itemName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemName.setTextColor(Color.parseColor("#DF3435"))
            checkBox.visibility = View.INVISIBLE
        } else if (!item.isCompleted && flag) {
            itemName.paintFlags = 0
            itemName.setTextColor(Color.parseColor("#363638"))
            checkBox.visibility = View.VISIBLE
        } else {
            itemName.paintFlags = 0
            itemName.setTextColor(Color.parseColor("#FFE1DF"))
            checkBox.visibility = View.VISIBLE
        }
    }

    // Defining viewHolder for TaskItem: TODO: Requires optimization
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView? = itemView.findViewById(R.id.task_item)
        val checkBox: CheckBox? = itemView.findViewById(R.id.checkbox_done)

        val itemName2: TextView? = itemView.findViewById(R.id.task_item_2)
        val checkBox2: CheckBox? = itemView.findViewById(R.id.checkbox_done_2)

    }
}