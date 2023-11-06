package projects.school.communication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import projects.school.communication.R
import projects.school.communication.model.Course

class SearchRecyclerAdapter(private val list: List<Course>) : RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.apply {
            headerText.text = list[position].header
            headerText.setLines(3)
            //image.setImageDrawable(ResourcesCompat.getDrawable(holder.image.context.resources, list[position].image, null)) //to delete
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }



    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val image: ImageView = view.findViewById(R.id.image_view)
        val headerText: TextView = view.findViewById(R.id.header)
        val favorite: ImageView = view.findViewById(R.id.favorite)

    }

}