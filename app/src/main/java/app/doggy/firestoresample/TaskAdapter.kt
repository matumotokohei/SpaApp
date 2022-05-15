package app.doggy.firestoresample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.doggy.firestoresample.databinding.TaskListItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdapter : ListAdapter<Task, TaskViewHolder>(diffUtilItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = TaskListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TaskViewHolder(
    private val binding: TaskListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task) {
        binding.spaNameTextView.text = task.spa_name
        binding.spaStationTextView.text = task.spa_station
        binding.spaWalkTextView.text = task.spa_walk
        binding.spaTimeTextView.text = task.spa_time

        binding.spaClosingDayTextView.text = task.spa_closing_day
        binding.spaCost1TextView.text = task.spa_cost1.toString()
        binding.spaCost2TextView.text = task.spa_cost2.toString()
        binding.spaNoteTextView.text = task.spa_note

    }
}

private val diffUtilItemCallback = object : DiffUtil.ItemCallback<Task>() {
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }
}