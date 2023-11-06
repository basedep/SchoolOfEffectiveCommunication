package projects.school.communication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import projects.school.communication.R
import projects.school.communication.model.Course

class CoursesRecyclerAdapter(private val list: List<Course>) : RecyclerView.Adapter<CoursesRecyclerAdapter.CoursesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        return CoursesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {


        holder.apply {
            text.text = list[position].header
            linearLayout.visibility = View.VISIBLE
            progressBar.progress = list[position].progress!!
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class CoursesViewHolder(view: View): RecyclerView.ViewHolder(view){

        val text: TextView = view.findViewById(R.id.header)
        val image: ImageView = view.findViewById(R.id.image_view)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val linearLayout: LinearLayout = view.findViewById(R.id.linearLayout)
        val favorite: ImageView = view.findViewById(R.id.favorite)

    }

}