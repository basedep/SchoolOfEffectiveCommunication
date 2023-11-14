package projects.school.communication.utils

import androidx.recyclerview.widget.DiffUtil
import projects.school.communication.model.Course

object MyDiffUtil : DiffUtil.ItemCallback<Course>(){

    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
       return oldItem == newItem
    }


}