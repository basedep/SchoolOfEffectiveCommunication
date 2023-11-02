package projects.school.comunication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import projects.school.comunication.R
import projects.school.comunication.model.Course

class HomeChildRecyclerAdapter(private val list: List<Course>) : RecyclerView.Adapter<HomeChildRecyclerAdapter.ChildViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {

        //inflate child view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_nested_list_item, parent, false)

        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {

        holder.apply {
            header.text = list[position].header
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val header: TextView = itemView.findViewById(R.id.item_header_text)
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
        val favoriteImage: ImageView = itemView.findViewById(R.id.favorite)


    }
}